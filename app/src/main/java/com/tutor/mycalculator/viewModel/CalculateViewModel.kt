package com.tutor.mycalculator.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutor.mycalculator.persentation.calculate.CalculateEvent
import com.tutor.mycalculator.persentation.calculate.CalculateState
import com.tutor.mycalculator.repository.CalculatorRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

@OptIn(ExperimentalCoroutinesApi::class)
class CalculateViewModel(
	private val calculateRepository: CalculatorRepository
) : ViewModel() {
	private val isSearch = MutableStateFlow("")
	private var notes =
		isSearch.flatMapLatest { search ->
			if (search == "") {
				calculateRepository.getAllCalculators()
			} else {
				calculateRepository.getCalculatorsByIds(search.toInt())
			}
		}.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
	val _state = MutableStateFlow(CalculateState())
	val state: StateFlow<CalculateState> = combine(
		_state,
		notes
	) { state, notes ->
		state.copy(
			data = notes
		)
	}.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CalculateState())

	fun onEvent(event: CalculateEvent) {
		when (event) {
			is CalculateEvent.Delete -> {
				calculateRepository.deleteCalculator(event.calculator)
			}

			is CalculateEvent.Save -> {
				calculateRepository.insertCalculator(event.calculator)
			}

			is CalculateEvent.Search -> {
				isSearch.value = event.value
			}

			CalculateEvent.Data -> {

			}
		}
	}
}
//class CalculatorViewModelFactory(
//	private val repository: CalculatorRepository
//) : ViewModelProvider.Factory {
//	fun <T : ViewModel?> create(modelClass: Class<T>): T {
//		if (modelClass.isAssignableFrom(CalculatorViewModelFactory::class.java)) {
//			return CalculatorViewModelFactory(repository) as T
//		}
//		throw IllegalArgumentException("Unknown ViewModel class")
//	}
//}
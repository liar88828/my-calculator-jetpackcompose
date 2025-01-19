package com.tutor.mycalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.tutor.mycalculator.persentation.calculate.CalculateEvent
import com.tutor.mycalculator.persentation.calculate.CalculateState
import com.tutor.mycalculator.database.database.AppDatabase
import com.tutor.mycalculator.repository.CalculatorRepository
import com.tutor.mycalculator.screen.About
import com.tutor.mycalculator.screen.Home
import com.tutor.mycalculator.screen.Test
import com.tutor.mycalculator.ui.theme.MyCalculatorTheme
import com.tutor.mycalculator.viewModel.CalculateViewModel
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
	private val database by lazy {
		Room.databaseBuilder(
			applicationContext,
			AppDatabase::class.java,
			"notes.db"
		).build()
	}

	@Suppress("UNCHECKED_CAST")
	private val viewModel by viewModels<CalculateViewModel>(
		factoryProducer = {
			object : ViewModelProvider.Factory {
				override fun <T : ViewModel> create(modelClass: Class<T>): T {
					return CalculateViewModel(CalculatorRepository(database.calculatorDao())) as T
				}
			}
		}
	)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			MyCalculatorTheme {
				val calculateState by viewModel.state.collectAsState()
				val navController = rememberNavController()
				val eventHandler = viewModel::onEvent
				Navigation(
					calculateState,
					navController,
					eventHandler,
					Modifier,
				)
			}
		}
	}
}

@Composable
fun Navigation(
	calculateState: CalculateState,
	navController: NavHostController,
	eventHandler: (event: CalculateEvent) -> Unit,
	modifier: Modifier,
) {
	NavHost(
		modifier = modifier,
		navController = navController,
		startDestination = Routers.Home
	) {
		composable<Routers.Home> {
			Home(navController, modifier, eventHandler, calculateState)
		}

		composable<Routers.About> {
			About(navController, modifier)
		}

		composable<Routers.Test> {
			Test()
		}
	}

}

sealed interface Routers {
	@Serializable
	data object Home : Routers

	@Serializable
	data object About : Routers

	@Serializable
	data object Test : Routers
}

@Preview(showBackground = true)
@Composable
fun NavigationPrev(modifier: Modifier = Modifier) {
	Navigation(
		calculateState = CalculateState(),
		navController = rememberNavController(),
		modifier = Modifier,
		eventHandler = {}
	)
}
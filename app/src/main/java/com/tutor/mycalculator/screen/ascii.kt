package com.tutor.mycalculator.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tutor.mycalculator.Routers
import com.tutor.mycalculator.screen.components.MyTextField
import com.tutor.mycalculator.utils.AsciiToNumber
import com.tutor.mycalculator.utils.NumberToBytes



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsciiScreen(route: NavHostController, modifier: Modifier) {
//	LaunchedEffect(
//		asciiInput.value,
//	) {
//		val resultAscii = BytesToNumber(asciiInput.value)
//		if (resultAscii != null) {
////			result.value = resultAscii
//			decimalInput.value = resultAscii
//		}
//	}
//	LaunchedEffect(
//		decimalInput.value,
//	) {
//		val resultAscii = NumberToBytes(decimalInput.value)
//		if (resultAscii != null) {
////			result.value = resultAscii
//			asciiInput.value = resultAscii
//		}
//	}
	Scaffold(
		topBar = {
			TopAppBar(
				navigationIcon = {
					IconButton(modifier = Modifier,
						onClick = {
							route.navigate(Routers.Home)
						}
					) {
						Icon(
							imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
							contentDescription = "More Menu"
						)
					}
				},
				actions = {
					IconButton(modifier = Modifier,
						onClick = {
//							route.navigate(Routers.Home)
						}
					) {
						Icon(
							imageVector = Icons.Filled.MoreVert,
							contentDescription = "More Menu"
						)
					}
				},
				title = {
					Text("MyCalculator")
				}
			)
		}
	) { screenPadding ->
		Column(
			modifier = Modifier.padding(screenPadding)
		) {
			AsciiComponent(

			)
		}
	}
}

@Composable
fun AsciiComponent(
	modifier: Modifier = Modifier
) {
	val asciiInput = remember { mutableStateOf("") }
	val decimalInput = remember { mutableStateOf("") }
	val result = remember { mutableStateOf("") }
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(10.dp),
		verticalArrangement = Arrangement.spacedBy(10.dp)
	) {
		Text("Ascii")
		Row(
			modifier,
			horizontalArrangement = Arrangement.spacedBy(10.dp)
		) {
			MyTextField(
				modifier = modifier.weight(1f),
				label = "asciiInput",
				value = asciiInput.value,
				onValueChange = {
					asciiInput.value = it
					val resultAscii = AsciiToNumber(it)
					if (resultAscii != null) {
						decimalInput.value = resultAscii
					}
				}
			)
			MyTextField(
				modifier = modifier.weight(1f),
				label = "decimalInput",
				value = decimalInput.value,
				onValueChange = {
					decimalInput.value = it
					val resultAscii = NumberToBytes(it)
					if (resultAscii != null) {
						asciiInput.value = resultAscii
					}
				}
			)
		}
//				Button(onClick = {
//					val resultAscii = BytesToNumber(asciiInput.value)
//					if (resultAscii != null) {
//						result.value = resultAscii
//						decimalInput.value = resultAscii
//					}
//				}) {
//					Text("Convert Bytes to Number")
//				}
//
//				Spacer(modifier = Modifier.height(16.dp))
//				Text(
//					text = "Result: ${BytesToNumber(asciiInput.value)}",
//					style = MaterialTheme.typography.bodyLarge
//				)
	}
}

@Preview
@Composable
private fun AsciiPrev() {
	AsciiScreen(
		route = rememberNavController(),
		modifier = Modifier
	)

}
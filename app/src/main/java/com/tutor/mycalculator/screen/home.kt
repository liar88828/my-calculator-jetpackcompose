package com.tutor.mycalculator.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tutor.mycalculator.Navigation
import com.tutor.mycalculator.Routers
import com.tutor.mycalculator.persentation.calculate.CalculateEvent
import com.tutor.mycalculator.persentation.calculate.CalculateState
import com.tutor.mycalculator.database.entity.Calculator
import com.tutor.mycalculator.utils.byteStringToIntFromHexx
import com.tutor.mycalculator.utils.formatByteArray
import com.tutor.mycalculator.utils.intToByteArray
import java.time.Instant
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
	router: NavController,
	modifier: Modifier = Modifier,
	eventHandler: (event: CalculateEvent) -> Unit,
	state: CalculateState,
) {
	// Function to convert an integer to a byte array
	var test1 by remember { mutableStateOf("") }
	// Decimal and Hexadecimal inputs
	var num1 by remember { mutableStateOf("") }
	var num2 by remember { mutableStateOf("") }
	// Hexadecimal Calculations
	var hex1 by remember { mutableStateOf("") }
	var hex2 by remember { mutableStateOf("") }
	// Byte Calculations
	var byte1 by remember { mutableStateOf("") }
	var byte2 by remember { mutableStateOf("") }
	// Decimal Calculations
	val decimalResultAdd = remember(num1, num2) {
		val n1 = num1.toIntOrNull() ?: 0
		val n2 = num2.toIntOrNull() ?: 0
		(n1 + n2).toString()
	}
	val decimalResultLess = remember(num1, num2) {
		val n1 = num1.toIntOrNull() ?: 0
		val n2 = num2.toIntOrNull() ?: 0
		(n1 - n2).toString()
	}
	val decimalResultFold = remember(num1, num2) {
		val n1 = num1.toIntOrNull() ?: 0
		val n2 = num2.toIntOrNull() ?: 0
		(n1 * n2).toString()
	}
	val decimalResultDiv = remember(num1, num2) {
		val n1 = num1.toIntOrNull() ?: 0
		val n2 = num2.toIntOrNull() ?: 1 // Avoid division by zero
		(n1 / n2).toString()
	}
	val decimalResultMod = remember(num1, num2) {
		val n1 = num1.toIntOrNull() ?: 0
		val n2 = num2.toIntOrNull() ?: 1 // Avoid division by zero
		(n1 % n2).toString()
	}
	// Hexadecimal result
	val hexResultAdd = remember(hex1, hex2) {
		val h1 = hex1.toIntOrNull(16) ?: 0
		val h2 = hex2.toIntOrNull(16) ?: 0
		(h1 + h2).toString(16).uppercase()
	}
	val hexResultLess = remember(hex1, hex2) {
		val h1 = hex1.toIntOrNull(16) ?: 0
		val h2 = hex2.toIntOrNull(16) ?: 0
		(h1 - h2).coerceAtLeast(0).toString(16).uppercase() // Prevent negative values
	}
	val hexResultFold = remember(hex1, hex2) {
		val h1 = hex1.toIntOrNull(16) ?: 0
		val h2 = hex2.toIntOrNull(16) ?: 0
		(h1 * h2).toString(16).uppercase()
	}
	val hexResultDiv = remember(hex1, hex2) {
		val h1 = hex1.toIntOrNull(16) ?: 0
		val h2 = hex2.toIntOrNull(16) ?: 1 // Avoid division by zero
		(h1 / h2).toString(16).uppercase()
	}
	val hexResultMod = remember(hex1, hex2) {
		val h1 = hex1.toIntOrNull(16) ?: 0
		val h2 = hex2.toIntOrNull(16) ?: 1 // Avoid division by zero
		(h1 % h2).toString(16).uppercase()
	}
	// Byte Calculations
//	val byteResultAdd = remember(byte1, byte2) {
//		val b1 = byte1.toIntOrNull()?.coerceIn(0..255) ?: 0 // Ensure valid byte
//		val b2 = byte2.toIntOrNull()?.coerceIn(0..255) ?: 0
//		((b1 + b2) % 256).toString() // Keep result in byte range
//	}
//	val byteResultLess = remember(byte1, byte2) {
//		val b1 = byte1.toIntOrNull()?.coerceIn(0..255) ?: 0
//		val b2 = byte2.toIntOrNull()?.coerceIn(0..255) ?: 0
//		((b1 - b2 + 256) % 256).toString() // Ensure non-negative byte
//	}
//	val byteResultFold = remember(byte1, byte2) {
//		val b1 = byte1.toIntOrNull()?.coerceIn(0..255) ?: 0
//		val b2 = byte2.toIntOrNull()?.coerceIn(0..255) ?: 0
//		((b1 * b2) % 256).toString()
//	}
//	val byteResultDiv = remember(byte1, byte2) {
//		val b1 = byte1.toIntOrNull()?.coerceIn(0..255) ?: 0
//		val b2 = byte2.toIntOrNull()?.coerceIn(0..255) ?: 1 // Avoid division by zero
//		(b1 / b2).toString()
//	}
//	val byteResultMod = remember(byte1, byte2) {
//		val b1 = byte1.toIntOrNull()?.coerceIn(0..255) ?: 0
//		val b2 = byte2.toIntOrNull()?.coerceIn(0..255) ?: 1 // Avoid division by zero
//		(b1 % b2).toString()
//	}
//
	val scrollState = remember { LazyListState() }
	val firstVisibleItemIndex = remember { derivedStateOf { scrollState.firstVisibleItemIndex } }
	val visibilityState = remember { MutableTransitionState(true) }

	LaunchedEffect(
		firstVisibleItemIndex
	) {
		val shouldBeVisible = scrollState.firstVisibleItemIndex == 0 &&
				scrollState.firstVisibleItemScrollOffset < 50 ||
				firstVisibleItemIndex.value > 2
		visibilityState.targetState = shouldBeVisible
	}


	Scaffold(
		modifier = Modifier.fillMaxSize(),
		topBar = {
			CenterAlignedTopAppBar(
				navigationIcon = {
					IconButton(onClick = {
						router.navigate(Routers.About)
					}) {
						Icon(
							imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
							contentDescription = "Back"
						)
					}
				},
				actions = {
					IconButton(modifier = Modifier,
						onClick = {}
					) {
						Icon(
							imageVector = Icons.Filled.MoreVert,
							contentDescription = "More Menu"
						)
					}
				},
				title = {
					Text("MyCalculator")
//					Text(
////						"test"
//						firstVisibleItemIndex.value.toString()
////								+ "_" + shouldBeVisibleHex..isScrollInProgress.toString()
//								+ "_is_ : " + visibilityState.currentState.toString()
//					)
				}
			)
		}
	) { screenPadding ->
		Column(
			modifier = Modifier
				.padding(screenPadding)
				.fillMaxSize()
//				.background(Color.Red)
			,
			verticalArrangement = Arrangement.spacedBy(10.dp)
		) {
			Column(
				modifier
//					.fillMaxSize()
//					.verticalScroll(scrollState)
			) {
				// Decimal Section
//				AnimatedVisibility(
//					visible = firstVisibleItemIndex.value < 1,
////					visibleState = visibilityState,
//					enter = fadeIn(),
//					exit = fadeOut()
//				) {
				// Decimal Section
				Card(
					colors = CardDefaults.cardColors(containerColor = Color.Transparent)
				) {
					Column(
						modifier.padding(8.dp),
						verticalArrangement = Arrangement.spacedBy(10.dp)
					) {
						Text("Decimal")

						MyTextField(
							value = num1,
							label = "Input Number 1",
							onValueChange = {
								num1 = it
								hex1 = it.toIntOrNull()?.toString(16)?.uppercase() ?: ""
								// Convert the input to Byte and update byte1
								// Convert the input to an integer and then to a byte array
								val number = it.toIntOrNull() ?: 0 // Safely handle invalid input
								val byteArray = intToByteArray(number)
								// Format the byte array into a string with \x prefix
								byte1 = formatByteArray(byteArray)
							}
						)

						MyTextField(
							value = num2,
							label = "Input Number 2",
							onValueChange = {
								num2 = it
								hex2 = it.toIntOrNull()?.toString(16)?.uppercase() ?: ""
								// Convert the input to Byte and update byte1
								// Convert the input to an integer and then to a byte array
								val number = it.toIntOrNull() ?: 0 // Safely handle invalid input
								val byteArray = intToByteArray(number)
								// Format the byte array into a string with \x prefix
								byte2 = formatByteArray(byteArray)
							}
						)

						Row(
							modifier = Modifier.fillMaxWidth(),
							horizontalArrangement = Arrangement.spacedBy(10.dp)
						) {
							listOf(
								"Add" to decimalResultAdd,
								"Less" to decimalResultLess,
								"Fold" to decimalResultFold,
								"Div" to decimalResultDiv,
								"Mod" to decimalResultMod
							).forEach { (label, value) ->
								MyResult(
									modifier = Modifier.weight(1f),
									value = value,
									label = label
								)
							}
						}
					}
				}
//				}
				// Hexadecimal Section
//				AnimatedVisibility(
//					visible = firstVisibleItemIndex.value < 2,
////					visibleState = visibilityState,
//					enter = fadeIn(),
//					exit = fadeOut()
//				) {
				// Hexadecimal Section
				Card(
					colors = CardDefaults.cardColors(containerColor = Color.Transparent)
				) {
					Column(
						modifier.padding(8.dp),
						verticalArrangement = Arrangement.spacedBy(10.dp)
					) {
						Text("Hexadecimal")

						MyTextField(
							value = hex1,
							label = "Input Hex 1",
							onValueChange = {
								val myNum = it.toIntOrNull(16)?.toString() ?: ""
								val number = it.toIntOrNull(16) ?: 0 // Safely handle invalid input
								val byteArray = intToByteArray(number)
								num1 = myNum
								hex1 = it
								byte1 = formatByteArray(byteArray)
							}
						)

						MyTextField(
							value = hex2,
							label = "Input Hex 2",
							onValueChange = {
								val myNum = it.toIntOrNull(16)?.toString() ?: ""
								val number = it.toIntOrNull(16) ?: 0 // Safely handle invalid input
								val byteArray = intToByteArray(number)
								num2 = myNum
								hex2 = it
								byte2 = formatByteArray(byteArray)
							}
						)

						Row(
							modifier = Modifier.fillMaxWidth(),
							horizontalArrangement = Arrangement.spacedBy(10.dp)
						) {
							listOf(
								"Add" to hexResultAdd,
								"Less" to hexResultLess,
								"Fold" to hexResultFold,
								"Div" to hexResultDiv,
								"Mod" to hexResultMod
							).forEach { (label, value) ->
								MyResult(
									modifier = Modifier.weight(1f),
									value = value,
									label = label
								)
							}
						}
					}
				}
//				}
				// byte Section
//				AnimatedVisibility(
//					visible = firstVisibleItemIndex.value < 1,
////					visibleState = visibilityState,
//					enter = fadeIn(),
//					exit = fadeOut()
//				) {
				Card(
					colors = CardDefaults.cardColors(containerColor = Color.Transparent)
				) {
					Column(
						modifier.padding(8.dp),
						verticalArrangement = Arrangement.spacedBy(10.dp)
					) {
						Text("Bytes ${test1}")

						MyTextField(
							value = byte1,
							label = "Input Byte 1",
							onValueChange = {
								try {
									// Convert byte string to integer
									val number = byteStringToIntFromHexx(it)
									// Update num1 and hex1 based on the parsed integer
									num1 = number.toString()
									hex1 = number.toString(16).uppercase()
								} catch (e: Exception) {
									test1 = e.message.toString()
								}
								byte1 = it
							}
						)

						MyTextField(
							value = byte2,
							label = "Input Byte 2",
							onValueChange = {
								try {
									val number = byteStringToIntFromHexx(it)
									num2 = number.toString()
									hex2 = number.toString(16).uppercase()
								} catch (e: Exception) {
									test1 = e.message.toString()
								}
								byte1 = it
							}
						)
//							Row(
//								modifier = Modifier.fillMaxWidth(),
//								horizontalArrangement = Arrangement.spacedBy(10.dp)
//							) {
//								listOf(
//									"Add" to byteResultAdd,
//									"Less" to byteResultLess,
//									"Fold" to byteResultFold,
//									"Div" to byteResultDiv,
//									"Mod" to byteResultMod
//								).forEach { (label, value) ->
//									MyResult(
//										modifier = Modifier.weight(1f),
//										value = value,
//										label = label
//									)
//								}
//							}//Row
//
					}
				}
//				}
//				AnimatedVisibility(
//					visible = firstVisibleItemIndex.value < 3,
//					enter = fadeIn(),
//					exit = fadeOut()
//				) {
				Column(
					modifier.padding(8.dp),
				) {
					Button(
						onClick = {
							num1 = ""
							num2 = ""
							hex1 = ""
							hex2 = ""
							byte1 = ""
							byte2 = ""
						},
						modifier = modifier
							.fillMaxWidth(),
						shape = RoundedCornerShape(8.dp), // Round the inner area
					) {
						Text("Reset")
					}

					Button(
						onClick = {
							eventHandler(
								CalculateEvent.Save(
									Calculator(
										num1 = num1,
										num2 = num2,
										createdAt = Date.from(Instant.now())
									)
								)
							)
						},
						modifier = modifier
							.fillMaxWidth(),
						shape = RoundedCornerShape(8.dp), // Round the inner area
					) {
						Text("Save")
					}
				}
			}
//			}
			LazyColumn(
				state = scrollState,
				modifier = Modifier
					.fillMaxSize()
					.padding(8.dp),
				verticalArrangement = Arrangement.spacedBy(8.dp)
			) {
				items(state.data) { item ->
					NoteItem(
						onDeleteAction = { eventHandler(CalculateEvent.Delete(item)) },
						item = item,
						setValueAction = {
							num1 = item.num1
							num2 = item.num2
							hex1 = item.num1.toIntOrNull()?.toString(16)?.uppercase() ?: ""
							hex2 = item.num2.toIntOrNull()?.toString(16)?.uppercase() ?: ""
						}
					)
				}
			}
		}
	}
}

@Composable
fun NoteItem(
	onDeleteAction: () -> Unit,
	item: Calculator,
	setValueAction: () -> Unit,
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.clip(RoundedCornerShape(10.dp))
			.background(MaterialTheme.colorScheme.primaryContainer)
			.padding(12.dp)
	) {
		Column(
			modifier = Modifier.weight(1f)
		) {
			Text("Number")
			Text(
				text = item.num1,
				fontSize = 16.sp,
				color = MaterialTheme.colorScheme.onSecondaryContainer
			)

			Spacer(modifier = Modifier.height(8.dp))

			Text(
				text = item.num2,
				fontSize = 16.sp,
				color = MaterialTheme.colorScheme.onSecondaryContainer
			)
		}

		Column(
			modifier = Modifier.weight(1f)
		) {
			Text("Hex")
			Text(
				text = item.num1.toIntOrNull()?.toString(16)?.uppercase() ?: "",
				fontSize = 16.sp,
				color = MaterialTheme.colorScheme.onSecondaryContainer
			)

			Spacer(modifier = Modifier.height(8.dp))

			Text(
				text = item.num2.toIntOrNull()?.toString(16)?.uppercase() ?: "",
				fontSize = 16.sp,
				color = MaterialTheme.colorScheme.onSecondaryContainer
			)
		}

		IconButton(
			onClick = setValueAction
		) {
			Icon(
				imageVector = Icons.Rounded.Check,
				contentDescription = "Set",
				modifier = Modifier.size(35.dp),
				tint = MaterialTheme.colorScheme.onPrimaryContainer
			)
		}
		IconButton(
			onClick = onDeleteAction
		) {
			Icon(
				imageVector = Icons.Rounded.Delete,
				contentDescription = "Delete Note",
				modifier = Modifier.size(35.dp),
				tint = MaterialTheme.colorScheme.onPrimaryContainer
			)
		}
	}
}

@Composable
private fun MyResult(
	modifier: Modifier = Modifier,
	value: String,
	label: String
) {
	TextField(
		modifier = modifier,
		label = { Text(label) },
		value = value,
		onValueChange = {},
		readOnly = true,
		colors = TextFieldDefaults.colors(
			cursorColor = Color.Black,
			disabledLabelColor = Color.Blue,
			focusedIndicatorColor = Color.Transparent,
			unfocusedIndicatorColor = Color.Transparent
		),
		shape = RoundedCornerShape(8.dp), // Round the inner area
	)

}

@Composable
private fun MyTextField(
	label: String,
	value: String,
	onValueChange: (String) -> Unit
) {
	TextField(
		modifier = Modifier
			.fillMaxWidth()
//			.clip(RoundedCornerShape(8.dp)), // Ensures the border corners are respected
		, shape = RoundedCornerShape(8.dp), // Round the inner area
		label = { Text(label) },
		value = value,
		onValueChange = onValueChange,
		colors = TextFieldDefaults.colors(
//							backgroundColor = Color.Transparent // Make sure the background doesn't overlap with the border
//							backgroundColor = lightBlue,
			cursorColor = Color.Black,
			disabledLabelColor = Color.Blue,
			focusedIndicatorColor = Color.Transparent,
			unfocusedIndicatorColor = Color.Transparent
		)
	)
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
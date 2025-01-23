package com.tutor.mycalculator.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tutor.mycalculator.Navigation
import com.tutor.mycalculator.Routers
import com.tutor.mycalculator.persentation.calculate.CalculateEvent
import com.tutor.mycalculator.persentation.calculate.CalculateState
import com.tutor.mycalculator.screen.components.MyResult
import com.tutor.mycalculator.screen.components.MyTextField
import com.tutor.mycalculator.utils.byteStringToIntFromHexx
import com.tutor.mycalculator.utils.formatByteArray
import com.tutor.mycalculator.utils.intToByteArray

data class BottomNavigationItem(
	val title: String,
	val selectedIcon: ImageVector,
	val unselectedIcon: ImageVector,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
	navController: NavController,
	modifier: Modifier = Modifier,
	eventHandler: (event: CalculateEvent) -> Unit,
	state: CalculateState,
) {
	val itemsBottomNavigationItem = listOf(
		BottomNavigationItem(
			title = "Home",
			selectedIcon = Icons.Filled.Home,
			unselectedIcon = Icons.Outlined.Home,
		),
		BottomNavigationItem(
			title = "Reservation",
			selectedIcon = Icons.Filled.AccountCircle,
			unselectedIcon = Icons.Outlined.AccountCircle
		),
		BottomNavigationItem(
			title = "Profile",
			selectedIcon = Icons.Filled.Person,
			unselectedIcon = Icons.Outlined.Person,
		)
	)
	var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
//	var expanded by remember {
//		mutableStateOf(false)
//	}
//	val isSuccessful = true// by dashboardViewModel.isSuccessful.observeAsState()
//
//	if (isSuccessful == true) {
//		navController.navigate("login")
//	}
//	var openDialog by remember { mutableStateOf(false) }
//
//	if (openDialog) {
////		ChangePasswordDialog(dashboardViewModel = dashboardViewModel, setShowDialog = { openDialog = it }, navController = navController)
//
//	}
	val scrollState = rememberScrollState()
	var test1 by remember { mutableStateOf("") }
	var byte1 by remember { mutableStateOf("") }
	var byte2 by remember { mutableStateOf("") }

	val (decimalState, decimalResult) = useDecimalCalculations()
	val (hexState, hexResult) = useHexCalculations()
	val (binState, binaryResult) = useBinaryCalculations()

	val (num1, num2) = decimalState
	val (hex1, hex2) = hexState
	val (bin1, bin2) = binState

	Scaffold(
		modifier = Modifier.fillMaxSize(),
		bottomBar = {
			NavigationBar {
				itemsBottomNavigationItem.forEachIndexed { index, item ->
					NavigationBarItem(
						selected = selectedItemIndex == index,
						onClick = {
							selectedItemIndex = index
							when (index) {
								0 -> navController.navigate(Routers.Home)
								1 -> navController.navigate(Routers.Ascii)
							}
						},
						label = {
							Text(text = item.title)
						},
						icon = {
							Icon(
								imageVector =
								if (selectedItemIndex == index)
									item.selectedIcon
								else item.unselectedIcon,
								contentDescription = item.title
							)
						}
					)
				}
			}
		},
		topBar = {
			CenterAlignedTopAppBar(
				navigationIcon = {
					IconButton(onClick = {
						navController.navigate(Routers.Ascii)
					}) {
						Icon(
							imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
							contentDescription = "Back"
						)
					}
				},
//				actions = {
//					IconButton(onClick = { expanded = true }) {
//						Icon(
//							imageVector = Icons.Filled.Menu,
//							contentDescription = "Localized description"
//						)
//					}
//					DropdownMenu(
//						expanded = expanded,
//						onDismissRequest = { expanded = false },
//					) {
//						DropdownMenuItem(
//							text = {
//								Text(text = "Change Password")
//							},
//							onClick = {
//								expanded = false
//								openDialog = true
//							}
//						)
//						DropdownMenuItem(
//							text = {
//								Text(text = "Logout")
//							},
//							onClick = {
//								expanded = false
////								dashboardViewModel.logout()
//							}
//						)
//					}
//				},
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
					.fillMaxSize()
					.verticalScroll(scrollState)
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
						Row(
							modifier = Modifier.fillMaxWidth(),
							horizontalArrangement = Arrangement.spacedBy(10.dp)
						) {
							MyTextField(
								modifier = Modifier.weight(1f),
								value = num1.value,
								label = "Input Number 1",
								onValueChange = {
									num1.value = it
									bin1.value = it.toIntOrNull()?.toString(2)
										?: "" // Use `toString(2)` to convert to binary
									hex1.value = it.toIntOrNull()?.toString(16)?.uppercase() ?: ""
									// Convert the input to Byte and update byte1
									// Convert the input to an integer and then to a byte array
									val number =
										it.toIntOrNull() ?: 0 // Safely handle invalid input
									val byteArray = intToByteArray(number)
									// Format the byte array into a string with \x prefix
									byte1 = formatByteArray(byteArray)
								}
							)

							MyTextField(
								modifier = Modifier.weight(1f),
								value = num2.value,
								label = "Input Number 2",
								onValueChange = {
									num2.value = it
									bin2.value = it.toIntOrNull()?.toString(2)
										?: "" // Use `toString(2)` to convert to binary
									hex2.value = it.toIntOrNull()?.toString(16)?.uppercase() ?: ""
									// Convert the input to Byte and update byte1
									// Convert the input to an integer and then to a byte array
									val number =
										it.toIntOrNull() ?: 0 // Safely handle invalid input
									val byteArray = intToByteArray(number)
									// Format the byte array into a string with \x prefix
									byte2 = formatByteArray(byteArray)
								}
							)
						}

						Row(
							modifier = Modifier.fillMaxWidth(),
							horizontalArrangement = Arrangement.spacedBy(10.dp)
						) {
							decimalResult.forEach { (label, value) ->
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
						Row(
							modifier = Modifier.fillMaxWidth(),
							horizontalArrangement = Arrangement.spacedBy(10.dp)
						) {
							MyTextField(
								modifier = Modifier.weight(1f),
								value = hex1.value,
								label = "Input Hex 1",
								onValueChange = {
									val myNum = it.toIntOrNull(16)?.toString() ?: ""
									val number =
										it.toIntOrNull(16) ?: 0 // Safely handle invalid input
									val byteArray = intToByteArray(number)
									num1.value = myNum
									hex1.value = it
									byte1 = formatByteArray(byteArray)
								}
							)

							MyTextField(
								modifier = Modifier.weight(1f),
								value = hex2.value,
								label = "Input Hex 2",
								onValueChange = {
									val myNum = it.toIntOrNull(16)?.toString() ?: ""
									val number =
										it.toIntOrNull(16) ?: 0 // Safely handle invalid input
									val byteArray = intToByteArray(number)
									num2.value = myNum
									hex2.value = it
									byte2 = formatByteArray(byteArray)
								}
							)
						}
						Row(
							modifier = Modifier.fillMaxWidth(),
							horizontalArrangement = Arrangement.spacedBy(10.dp)
						) {
							hexResult.forEach { (label, value) ->
								MyResult(
									modifier = Modifier.weight(1f),
									value = value,
									label = label
								)
							}
						}
					}
				}
				Card(
					colors = CardDefaults.cardColors(containerColor = Color.Transparent)
				) {
					Column(
						modifier.padding(8.dp),
						verticalArrangement = Arrangement.spacedBy(10.dp)
					) {
						Text("Binary")
						Row(
							modifier = Modifier.fillMaxWidth(),
							horizontalArrangement = Arrangement.spacedBy(10.dp)
						) {
							MyTextField(
								modifier = Modifier.weight(1f),
								value = bin1.value,
								label = "Input Bin 1",
								onValueChange = {
									bin1.value = it
									val decimal = (it.toIntOrNull(2)?.toString()
										?: "Invalid")
									num1.value =
										decimal // Convert binary to decimal or show "Invalid"
									hex1.value =
										decimal.toIntOrNull()?.toString(16)?.uppercase() ?: ""
								}
							)

							MyTextField(
								modifier = Modifier.weight(1f),
								value = bin2.value,
								label = "Input Bin 2",
								onValueChange = {
									bin2.value = it
									val decimal = (it.toIntOrNull(2)?.toString()
										?: "Invalid")
									num2.value =
										decimal // Convert binary to decimal or show "Invalid"
									hex2.value =
										decimal.toIntOrNull()?.toString(16)?.uppercase() ?: ""
								}
							)
						}
						Row(
							modifier = Modifier.fillMaxWidth(),
							horizontalArrangement = Arrangement.spacedBy(10.dp)
						) {
							binaryResult.forEach { (label, value) ->
								MyResult(
									modifier = Modifier.weight(1f),
									value = value,
									label = label
								)
							}
						}
					}
				}


				Card(
					colors = CardDefaults.cardColors(containerColor = Color.Transparent)
				) {
					Column(
						modifier.padding(8.dp),
						verticalArrangement = Arrangement.spacedBy(10.dp)
					) {
						Text("Bytes ${test1}")
						Row(
							modifier = Modifier.fillMaxWidth(),
							horizontalArrangement = Arrangement.spacedBy(10.dp)
						) {
							MyTextField(
								modifier = Modifier.weight(1f),
								value = byte1,
								label = "Input Byte 1",
								onValueChange = {
									try {
										// Convert byte string to integer
										val number = byteStringToIntFromHexx(it)
										// Update num1 and hex1 based on the parsed integer
										num1.value = number.toString()
										hex1.value = number.toString(16).uppercase()
									} catch (e: Exception) {
										test1 = e.message.toString()
									}
									byte1 = it
								}
							)

							MyTextField(
								modifier = Modifier.weight(1f),
								value = byte2,
								label = "Input Byte 2",
								onValueChange = {
									try {
										val number = byteStringToIntFromHexx(it)
										num2.value = number.toString()
										hex2.value = number.toString(16).uppercase()
									} catch (e: Exception) {
										test1 = e.message.toString()
									}
									byte1 = it
								}
							)
						}
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
				AsciiComponent()
//
				Column(
					modifier.padding(8.dp),
				) {
					Button(
						onClick = {
							num1.value = ""
							num2.value = ""
							hex1.value = ""
							hex2.value = ""
							byte1 = ""
							byte2 = ""
						},
						modifier = modifier
							.fillMaxWidth(),
						shape = RoundedCornerShape(8.dp), // Round the inner area
					) {
						Text("Reset")
					}
//					Button(
//						onClick = {
//							eventHandler(
//								CalculateEvent.Save(
//									Calculator(
//										num1 = num1,
//										num2 = num2,
//										createdAt = Date.from(Instant.now())
//									)
//								)
//							)
//						},
//						modifier = modifier
//							.fillMaxWidth(),
//						shape = RoundedCornerShape(8.dp), // Round the inner area
//					) {
//						Text("Save")
//					}
				}
			}
//			}
//			LazyColumn(
//				state = scrollState,
//				modifier = Modifier
//					.fillMaxSize()
//					.padding(8.dp),
//				verticalArrangement = Arrangement.spacedBy(8.dp)
//			) {
//				items(state.data) { item ->
//					NoteItem(
//						onDeleteAction = { eventHandler(CalculateEvent.Delete(item)) },
//						item = item,
//						setValueAction = {
//							num1 = item.num1
//							num2 = item.num2
//							hex1.value = item.num1.toIntOrNull()?.toString(16)?.uppercase() ?: ""
//							hex2.value = item.num2.toIntOrNull()?.toString(16)?.uppercase() ?: ""
//						}
//					)
//				}
//			}
		}
	}
}

@Composable
fun useBinaryCalculations(): Pair<Pair<MutableState<String>, MutableState<String>>, Map<String, String>> {
	// binary Calculation
	val bin1 = remember { mutableStateOf("") }
	val bin2 = remember { mutableStateOf("") }
	// Binary Calculations
	val binaryResultAnd = remember(bin1.value, bin2.value) {
		val n1 = bin2.value.toIntOrNull(2) ?: 0 // Convert binary string to decimal
		val n2 = bin2.value.toIntOrNull(2) ?: 0
		(n1 and n2).toString(2) // Perform AND operation and convert back to binary
	}
	val binaryResultXor = remember(bin1.value, bin2.value) {
		val n1 = bin1.value.toIntOrNull(2) ?: 0 // Convert binary string to decimal
		val n2 = bin2.value.toIntOrNull(2) ?: 0
		(n1 xor n2).toString(2) // Perform XOR operation and convert back to binary
	}
	return Pair(
		first = bin1 to bin2, // Returning num1 and num2
		second = mapOf(
			"And" to binaryResultAnd,
			"Xor" to binaryResultXor,
		)
	)
}

@Composable
fun useDecimalCalculations(): Pair<Pair<MutableState<String>, MutableState<String>>, Map<String, String>> {
	// Remember state for numbers
	val num1 = remember { mutableStateOf("") }
	val num2 = remember { mutableStateOf("") }
	// Decimal Calculations
	val decimalResultAdd = remember(num1.value, num2.value) {
		val n1 = num1.value.toIntOrNull() ?: 0
		val n2 = num2.value.toIntOrNull() ?: 0
		(n1 + n2).toString()
	}
	val decimalResultLess = remember(num1.value, num2.value) {
		val n1 = num1.value.toIntOrNull() ?: 0
		val n2 = num2.value.toIntOrNull() ?: 0
		(n1 - n2).toString()
	}
	val decimalResultFold = remember(num1.value, num2.value) {
		val n1 = num1.value.toIntOrNull() ?: 0
		val n2 = num2.value.toIntOrNull() ?: 0
		(n1 * n2).toString()
	}
	val decimalResultDiv = remember(num1.value, num2.value) {
		val n1 = num1.value.toIntOrNull() ?: 0
		val n2 = num2.value.toIntOrNull() ?: 1 // Avoid division by zero
		(n1 / n2).toString()
	}
	val decimalResultMod = remember(num1.value, num2.value) {
		val n1 = num1.value.toIntOrNull() ?: 0
		val n2 = num2.value.toIntOrNull() ?: 1 // Avoid division by zero
		(n1 % n2).toString()
	}
	// Return num1, num2, and the calculation results as a Pair
	return Pair(
		first = num1 to num2, // Returning num1 and num2
		second = mapOf(
			"Add" to decimalResultAdd,
			"Sub" to decimalResultLess,
			"fold" to decimalResultFold,
			"Div" to decimalResultDiv,
			"Mod" to decimalResultMod
		)
	)
}

@Composable
fun useHexCalculations(): Pair<Pair<MutableState<String>, MutableState<String>>, Map<String, String>> {
	// Remember state for numbers
	val hex1 = remember { mutableStateOf("") }
	val hex2 = remember { mutableStateOf("") }
	// hexDecimal Calculations
	val hexResultAdd = remember(hex1.value, hex2.value) {
		val h1 = hex1.value.toIntOrNull(16) ?: 0
		val h2 = hex2.value.toIntOrNull(16) ?: 0
		(h1 + h2).toString(16).uppercase()
	}
	val hexResultLess = remember(hex1.value, hex2.value) {
		val h1 = hex1.value.toIntOrNull(16) ?: 0
		val h2 = hex2.value.toIntOrNull(16) ?: 0
		(h1 - h2).coerceAtLeast(0).toString(16).uppercase() // Prevent negative values
	}
	val hexResultFold = remember(hex1.value, hex2.value) {
		val h1 = hex1.value.toIntOrNull(16) ?: 0
		val h2 = hex2.value.toIntOrNull(16) ?: 0
		(h1 * h2).toString(16).uppercase()
	}
	val hexResultDiv = remember(hex1.value, hex2.value) {
		val h1 = hex1.value.toIntOrNull(16) ?: 0
		val h2 = hex2.value.toIntOrNull(16) ?: 1 // Avoid division by zero
		(h1 / h2).toString(16).uppercase()
	}
	val hexResultMod = remember(hex1.value, hex2.value) {
		val h1 = hex1.value.toIntOrNull(16) ?: 0
		val h2 = hex2.value.toIntOrNull(16) ?: 1 // Avoid division by zero
		(h1 % h2).toString(16).uppercase()
	}
	// Return num1, num2, and the calculation results as a Pair
	return Pair(
		first = hex1 to hex2, // Returning num1 and num2
		second = mapOf(
			"Add" to hexResultAdd,
			"Sub" to hexResultLess,
			"fold" to hexResultFold,
			"Div" to hexResultDiv,
			"Mod" to hexResultMod
		)
	)
}

@Preview(
	showBackground = true,
	heightDp = 1600
//	showSystemUi = true,
)
@Composable
fun NavigationPrev(modifier: Modifier = Modifier) {
	Navigation(
		calculateState = CalculateState(),
		navController = rememberNavController(),
		modifier = Modifier,
		eventHandler = {}
	)
}
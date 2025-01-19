package com.tutor.mycalculator.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import java.nio.file.WatchEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Test(modifier: Modifier = Modifier) {
	var num1 by remember { mutableStateOf("") }
	var num2 by remember { mutableStateOf("") }

	Scaffold(
		topBar = {
			TopAppBar(
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
				}
			)
		}
	) { screenPadding ->
		
		Column(
			modifier = Modifier.padding(screenPadding)
		) {
			TextField(
				modifier = Modifier,
				label = { Text("Input Number 2") },
				value = num1,
				onValueChange = { num1 = it },
			)

			TextField(
				modifier = Modifier,
				label = { Text("Input Number 2") },
				value = num2,
				onValueChange = { num2 = it },
			)
		}
	}
}
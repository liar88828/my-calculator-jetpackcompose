package com.tutor.mycalculator.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import androidx.navigation.NavHostController
import com.tutor.mycalculator.Routers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun About(route: NavHostController , modifier: Modifier) {
	var num1 by remember { mutableStateOf("") }
	var num2 by remember { mutableStateOf("") }

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
			 Text("Menu About")
		}
	}
}
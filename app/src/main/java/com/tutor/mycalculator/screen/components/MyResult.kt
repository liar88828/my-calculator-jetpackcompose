package com.tutor.mycalculator.screen.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyResult(
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
package com.tutor.mycalculator.screen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
  fun MyTextField(
	label: String,
	value: String,
	onValueChange: (String) -> Unit,
	modifier: Modifier = Modifier
) {
	TextField(
		modifier = modifier
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
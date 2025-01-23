package com.tutor.mycalculator.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutor.mycalculator.database.entity.Calculator

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
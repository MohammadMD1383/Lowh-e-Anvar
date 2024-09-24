package com.example.lowheanvar.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.lowheanvar.ContentManager
import com.example.lowheanvar.ui.components.RichEditor
import com.example.lowheanvar.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentPage(navController: NavController) {
	var title by remember { mutableStateOf(if (ContentManager.editNote) ContentManager.openNote!!.title else "") }
	var content by remember { mutableStateOf(if (ContentManager.editNote) ContentManager.openNote!!.content else "") }
	var titleHasError by remember { mutableStateOf(false) }
	
	Scaffold(
		topBar = {
			CenterAlignedTopAppBar(
				title = { Text(if (ContentManager.editNote) "Edit Note" else "New Note") },
				navigationIcon = {
					IconButton(onClick = {
						navController.popBackStack()
					}) {
						Icon(Icons.Rounded.Close, null)
					}
				},
				actions = {
					IconButton(onClick = {
						if (title.isBlank()) {
							titleHasError = true
							return@IconButton
						}
						
						if (ContentManager.editNote) {
							ContentManager.editNote = false
							ContentManager.editNote(title, content)
						} else {
							ContentManager.createNote(title, content)
						}
						navController.popBackStack()
					}) {
						Icon(Icons.Rounded.Check, null)
					}
				}
			)
		},
	) { contentPadding ->
		Column(
			modifier = Modifier
				.padding(contentPadding)
				.fillMaxSize()
		) {
			OutlinedTextField(
				textStyle = Typography.headlineMedium,
				value = title,
				isError = titleHasError,
				onValueChange = { title = it; titleHasError = false },
				supportingText = { if (titleHasError) Text("Title cannot be blank!") },
				placeholder = { Text("Title", style = Typography.headlineMedium) },
				modifier = Modifier.fillMaxWidth(),
				singleLine = true,
				colors = OutlinedTextFieldDefaults.colors(
					disabledBorderColor = Color.Transparent,
					unfocusedBorderColor = Color.Transparent,
					focusedBorderColor = Color.Transparent,
					errorBorderColor = Color.Transparent
				),
			)
			
			RichEditor(
				text = content,
				onTextChange = { content = it }
			)
		}
	}
}

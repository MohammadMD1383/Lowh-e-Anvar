package com.example.lowheanvar.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.lowheanvar.ui.theme.Typography
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class NewFolderDialog {
	sealed interface DialogResult {
		data object Cancel : DialogResult
		data class Create(val name: String) : DialogResult
	}
	
	private var newFolderName by mutableStateOf("")
	private var shown by mutableStateOf(false)
	private val channel = Channel<Boolean>()
	
	suspend fun show(): DialogResult {
		if (shown) {
			throw IllegalStateException("Dialog is already shown")
		}
		
		shown = true
		val result = channel.receive()
		shown = false
		val dialogResult = if (result) DialogResult.Create(newFolderName) else DialogResult.Cancel
		newFolderName = ""
		
		return dialogResult
	}
	
	@Composable
	operator fun invoke() {
		val scope = rememberCoroutineScope()
		
		if (shown) Dialog(
			onDismissRequest = {
				scope.launch {
					channel.send(false)
				}
			}
		) {
			Card {
				Column(
					modifier = Modifier.padding(16.dp)
				) {
					Text(
						text = "New Folder",
						style = Typography.headlineSmall,
						textAlign = TextAlign.Center,
						modifier = Modifier.fillMaxWidth()
					)
					
					Spacer(Modifier.height(8.dp))
					
					OutlinedTextField(
						value = newFolderName,
						onValueChange = { newFolderName = it },
						placeholder = { Text("Enter Folder Name") },
						modifier = Modifier.fillMaxWidth()
					)
					
					Spacer(Modifier.height(8.dp))
					
					Row {
						TextButton(
							onClick = {
								scope.launch {
									channel.send(false)
								}
							},
							modifier = Modifier.weight(1f)
						) {
							Text("Cancel")
						}
						
						TextButton(
							onClick = {
								// TODO: validation
								scope.launch {
									channel.send(true)
								}
							},
							modifier = Modifier.weight(1f)
						) {
							Text("Create")
						}
					}
				}
			}
		}
	}
}

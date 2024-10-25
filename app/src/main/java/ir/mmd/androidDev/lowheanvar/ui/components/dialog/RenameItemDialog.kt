package ir.mmd.androidDev.lowheanvar.ui.components.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ir.mmd.androidDev.lowheanvar.ContentManager
import ir.mmd.androidDev.lowheanvar.R
import ir.mmd.androidDev.lowheanvar.ifTrue
import ir.mmd.androidDev.lowheanvar.ui.theme.Typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class RenameItemDialog : BaseDialog<RenameItemDialog, String?, RenameItemDialog.RenameResult>() {
	sealed interface RenameResult {
		data object Cancel : RenameResult
		data class OK(val name: String) : RenameResult
	}
	
	enum class ItemType { Note, Folder }
	
	var itemType: ItemType? by mutableStateOf(null)
	var previousName = ""
	
	override fun before() {
		itemType = null
		previousName = ""
	}
	
	@Composable
	override fun Content(scope: CoroutineScope) {
		var name by remember { mutableStateOf("") }
		var problemText by remember { mutableStateOf("") }
		val hasError by remember {
			derivedStateOf {
				name.isBlank().ifTrue { problemText = "Name cannot be empty" } ||
					name.contains('/').ifTrue { problemText = "Name cannot contain '/' character" } ||
					(name == previousName).ifTrue { problemText = "Name cannot be the same as the previous name" } ||
					(if (itemType == ItemType.Folder) ContentManager.folders.any { it.name == name }
					else itemType == ItemType.Note && ContentManager.notes.any { it.title == name })
						.ifTrue { problemText = "An item with this name already exists" }
			}
		}
		
		Card {
			Column(
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = Modifier.padding(16.dp)
			) {
				Text("Renaming: $previousName", style = Typography.headlineSmall)
				
				OutlinedTextField(
					value = name,
					onValueChange = { name = it },
					placeholder = { Text("Enter new name") },
					supportingText = { if (hasError) Text(problemText) },
					singleLine = true,
					isError = hasError,
					modifier = Modifier.padding(top = 8.dp)
				)
				
				Row {
					TextButton(
						modifier = Modifier.weight(1f),
						onClick = {
							scope.launch { done(null) }
						}
					) {
						Text(stringResource(R.string.btn_cancel))
					}
					
					TextButton(
						enabled = !hasError,
						modifier = Modifier.weight(1f),
						onClick = {
							scope.launch { done(name) }
						}
					) {
						Text(stringResource(R.string.btn_ok))
					}
				}
			}
		}
	}
	
	override suspend fun onDismissRequest() = done(null)
	override fun createResult(result: String?) = if (result != null) RenameResult.OK(result) else RenameResult.Cancel
}

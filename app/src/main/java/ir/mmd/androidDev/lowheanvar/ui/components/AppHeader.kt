package ir.mmd.androidDev.lowheanvar.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ir.mmd.androidDev.lowheanvar.AppSettings
import ir.mmd.androidDev.lowheanvar.R
import ir.mmd.androidDev.lowheanvar.ui.controllers.SelectionController
import ir.mmd.androidDev.lowheanvar.ui.theme.CustomTheme

@Composable
fun AppHeader(selectionController: SelectionController) {
	if (selectionController.selectMode) {
		HeaderSelectionCounter(selectionController)
	} else {
		HeaderVector()
	}
}

@Composable
fun HeaderSelectionCounter(selectionController: SelectionController) {
	val folderText = if (selectionController.hasFolderSelection()) "${selectionController.foldersCount()} ${stringResource(R.string.txt_folders)}" else ""
	val noteText = if (selectionController.hasNoteSelection()) "${selectionController.notesCount()} ${stringResource(R.string.txt_notes)}" else ""
	val separator = if (folderText.isNotEmpty() && noteText.isNotEmpty()) "${stringResource(R.string.comma)} " else ""
	Text("$folderText$separator$noteText")
}

@Composable
fun HeaderVector() {
	Box(contentAlignment = Alignment.Center) {
		Icon(
			painter = painterResource(R.drawable.lowh_e_anvar_border),
			contentDescription = null,
			tint = if (CustomTheme.useCustomTheme) CustomTheme.headerBorder else AppSettings.headerBorder
		)
		Icon(
			painter = painterResource(R.drawable.lowh_e_anvar_text),
			contentDescription = null,
			tint = if (CustomTheme.useCustomTheme) CustomTheme.headerText else AppSettings.headerText
		)
	}
}

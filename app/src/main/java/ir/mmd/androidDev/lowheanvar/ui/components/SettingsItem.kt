package ir.mmd.androidDev.lowheanvar.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ir.mmd.androidDev.lowheanvar.ui.applyIf
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.ColorPickerDialog
import ir.mmd.androidDev.lowheanvar.ui.components.dialog.ColorPickerDialog.ColorResult
import kotlinx.coroutines.launch

@Composable
fun SettingsItem(
	title: @Composable () -> Unit,
	action: @Composable (() -> Unit)? = null,
	divider: Boolean = false,
	modifier: Modifier = Modifier,
	onClick: (() -> Unit)? = null
) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.then(modifier)
			.applyIf(onClick != null) {
				clickable(onClick = onClick!!)
			}
			.fillMaxWidth()
			.requiredHeight(72.dp)
			.padding(horizontal = 16.dp)
	) {
		Box(Modifier.weight(1f)) { title() }
		
		if (divider) VerticalDivider(
			thickness = 1.dp,
			modifier = Modifier
				.requiredHeight(32.dp)
				.padding(horizontal = 12.dp)
		)
		
		action?.invoke()
	}
}

@Composable
fun SettingsItem(
	title: String,
	action: @Composable (() -> Unit)? = null,
	divider: Boolean = false,
	modifier: Modifier = Modifier,
	onClick: (() -> Unit)? = null
) {
	SettingsItem(
		title = { Text(title) },
		action = action,
		divider = divider,
		modifier = modifier,
		onClick = onClick
	)
}

@Composable
fun TextSettingsItem(
	title: String,
	modifier: Modifier = Modifier,
	onClick: () -> Unit
) {
	SettingsItem(
		title = title,
		action = { Icon(Icons.AutoMirrored.Rounded.ArrowForwardIos, null) },
		modifier = modifier,
		onClick = onClick
	)
}

@Composable
fun SwitchSettingsItem(
	title: @Composable () -> Unit,
	checked: Boolean,
	onCheckedChange: (Boolean) -> Unit,
	modifier: Modifier = Modifier,
	onClick: (() -> Unit)? = null
) {
	SettingsItem(
		modifier = modifier,
		title = title,
		divider = onClick != null,
		action = {
			Switch(
				checked = checked,
				onCheckedChange = if (onClick == null) null else onCheckedChange
			)
		},
		onClick = onClick ?: {
			onCheckedChange(!checked)
		}
	)
}

@Composable
fun SwitchSettingsItem(
	title: String,
	checked: Boolean,
	onCheckedChange: (Boolean) -> Unit,
	modifier: Modifier = Modifier,
	onClick: (() -> Unit)? = null
) {
	SwitchSettingsItem(
		title = { Text(title) },
		checked = checked,
		onCheckedChange = onCheckedChange,
		modifier = modifier,
		onClick = onClick
	)
}

@Composable
fun ColorPreferenceSettingsItem(
	colorPickerDialog: ColorPickerDialog,
	title: String,
	color: Color,
	modifier: Modifier = Modifier,
	onResult: (Color) -> Unit
) {
	val scope = rememberCoroutineScope()
	
	SettingsItem(
		modifier = modifier,
		title = title,
		action = {
			ColorPreviewBox(
				size = 40.dp,
				shape = RoundedCornerShape12dp,
				color = color,
			)
		},
		onClick = {
			scope.launch {
				val result = colorPickerDialog.show { this.color = color }
				if (result is ColorResult.OK) {
					onResult(result.color)
				}
			}
		}
	)
}

@Composable
fun NumberPreferenceSettingsItem(
	title: String,
	value: Int,
	modifier: Modifier = Modifier,
	onValueChange: (Int) -> Unit
) {
	SettingsItem(
		modifier = modifier,
		title = title,
		action = {
			IconButton(onClick = { onValueChange(value - 1) }) {
				Icon(Icons.Rounded.Remove, null)
			}
			
			Text(value.toString())
			
			IconButton(onClick = { onValueChange(value + 1) }) {
				Icon(Icons.Rounded.Add, null)
			}
		}
	)
}

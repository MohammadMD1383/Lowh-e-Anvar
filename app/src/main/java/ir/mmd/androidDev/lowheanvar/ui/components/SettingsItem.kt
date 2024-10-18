package ir.mmd.androidDev.lowheanvar.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.mmd.androidDev.lowheanvar.ui.theme.LowheAnvarTheme

@Composable
fun SettingsItem(text: String, icon: ImageVector, onClick: () -> Unit) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.clickable(onClick = onClick)
			.padding(horizontal = 16.dp, vertical = 24.dp)
	) {
		Text(
			text = text,
			modifier = Modifier.weight(1f)
		)
		
		Icon(icon, null)
	}
}

@Preview
@Composable
private fun LightPreview() {
	LowheAnvarTheme(darkTheme = false) {
		SettingsItem("Some Text", Icons.AutoMirrored.Rounded.ArrowForwardIos) {}
	}
}

@Preview
@Composable
private fun DarkPreview() {
	LowheAnvarTheme(darkTheme = true) {
		SettingsItem("Some Text", Icons.AutoMirrored.Rounded.ArrowForwardIos) {}
	}
}

package ir.mmd.androidDev.lowheanvar.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun IconButton(
	icon: ImageVector,
	modifier: Modifier = Modifier,
	onClick: () -> Unit
) {
	IconButton(
		onClick = onClick,
		modifier = modifier
	) { Icon(icon, null) }
}

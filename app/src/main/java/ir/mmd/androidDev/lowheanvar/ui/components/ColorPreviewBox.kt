package ir.mmd.androidDev.lowheanvar.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

@Composable
fun ColorPreviewBox(
	color: Color,
	size: Dp,
	shape: Shape,
	modifier: Modifier = Modifier
) {
	Box(
		content = {},
		modifier = Modifier
			.then(modifier)
			.size(size)
			.background(
				color = color,
				shape = shape
			)
	)
}

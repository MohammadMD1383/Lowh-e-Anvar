package ir.mmd.androidDev.lowheanvar.ui

import androidx.compose.ui.Modifier

fun Modifier.applyIf(
	condition: Boolean,
	block: Modifier.() -> Modifier
) = if (condition) then(block()) else this

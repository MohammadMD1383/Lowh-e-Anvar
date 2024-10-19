package ir.mmd.androidDev.lowheanvar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.navigation.NavController

@Composable
operator fun PaddingValues.plus(other: PaddingValues): PaddingValues {
	val layoutDirection = LocalLayoutDirection.current
	return PaddingValues(
		start = calculateStartPadding(layoutDirection) + other.calculateStartPadding(layoutDirection),
		top = calculateTopPadding() + other.calculateTopPadding(),
		end = calculateEndPadding(layoutDirection) + other.calculateEndPadding(layoutDirection),
		bottom = calculateBottomPadding() + other.calculateBottomPadding(),
	)
}

fun NavController.navigateSingleTop(route: String) {
	navigate(route) {
		launchSingleTop = true
	}
}

fun Boolean.ifTrue(block: () -> Unit): Boolean {
	if (this) block()
	return this
}

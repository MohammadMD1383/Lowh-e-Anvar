package com.example.lowheanvar.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class ReusableComponent(private val component: @Composable () -> Unit) {
	@Composable
	operator fun invoke() {
		component()
	}
}

@Composable
fun rememberReusableComponent(component: @Composable () -> Unit): ReusableComponent {
	return remember { ReusableComponent(component) }
}

package ir.mmd.androidDev.lowheanvar.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import ir.mmd.androidDev.lowheanvar.AppSettings
import ir.mmd.androidDev.lowheanvar.R
import ir.mmd.androidDev.lowheanvar.ui.theme.CustomTheme

@Composable
fun AppHeaderVector() {
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

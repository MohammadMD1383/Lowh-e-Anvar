package ir.mmd.androidDev.lowheanvar.ui.theme

import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun dynamicCardColors(selected: Boolean) = CardDefaults.cardColors(containerColor = if (selected) MaterialTheme.colorScheme.primaryContainer else Color.Unspecified)

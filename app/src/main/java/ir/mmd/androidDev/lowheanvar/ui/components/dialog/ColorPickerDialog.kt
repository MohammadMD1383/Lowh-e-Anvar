package ir.mmd.androidDev.lowheanvar.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import ir.mmd.androidDev.lowheanvar.R
import ir.mmd.androidDev.lowheanvar.ui.theme.Typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ColorPickerDialog : BaseDialog<ColorPickerDialog, Color?, ColorPickerDialog.ColorResult>() {
	sealed interface ColorResult {
		data object Cancel : ColorResult
		data class OK(val color: Color) : ColorResult
	}
	
	var color: Color? by mutableStateOf(null)
	
	override fun before() {
		color = null
	}
	
	@Composable
	override fun Content(scope: CoroutineScope) {
		val colorPickerController = rememberColorPickerController()
		
		Card(shape = RoundedCornerShape(24.dp)) {
			Column(horizontalAlignment = Alignment.CenterHorizontally) {
				Text(
					text = stringResource(R.string.txt_pick_a_color),
					style = Typography.headlineMedium,
					modifier = Modifier.padding(top = 16.dp)
				)
				
				HsvColorPicker(
					controller = colorPickerController,
					initialColor = color,
					modifier = Modifier
						.padding(16.dp)
						.fillMaxWidth()
						.aspectRatio(1f)
				)
				
				AlphaSlider(
					controller = colorPickerController,
					initialColor = color,
					borderRadius = 30.dp,
					modifier = Modifier
						.padding(horizontal = 16.dp)
						.fillMaxWidth()
						.height(30.dp)
				)
				Spacer(Modifier.height(16.dp))
				BrightnessSlider(
					controller = colorPickerController,
					initialColor = color,
					borderRadius = 30.dp,
					modifier = Modifier
						.padding(horizontal = 16.dp)
						.fillMaxWidth()
						.height(30.dp)
				)
				Spacer(Modifier.height(16.dp))
				Box(
					modifier = Modifier
						.size(72.dp)
						.clip(RoundedCornerShape(16.dp))
						.background(colorPickerController.selectedColor.value),
					content = {}
				)
				Spacer(Modifier.height(16.dp))
				Row(Modifier.padding(bottom = 8.dp)) {
					TextButton(
						onClick = {
							scope.launch {
								done(null)
							}
						},
						modifier = Modifier.weight(1f)
					) {
						Text(stringResource(R.string.btn_cancel))
					}
					TextButton(
						onClick = {
							scope.launch {
								done(colorPickerController.selectedColor.value)
							}
						},
						modifier = Modifier.weight(1f)
					) {
						Text(stringResource(R.string.btn_ok))
					}
				}
			}
		}
	}
	
	override suspend fun onDismissRequest() = done(null)
	override fun createResult(result: Color?): ColorResult = if (result != null) ColorResult.OK(result) else ColorResult.Cancel
}

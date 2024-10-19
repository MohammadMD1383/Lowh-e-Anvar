package ir.mmd.androidDev.lowheanvar

import android.app.Application
import ir.mmd.androidDev.lowheanvar.ui.theme.AppSettings
import ir.mmd.androidDev.lowheanvar.ui.theme.CustomTheme

class App : Application() {
	override fun onCreate() {
		super.onCreate()
		AppSettings.load(this)
		CustomTheme.load(this)
		ContentManager = ContentManagerClass(this)
	}
}

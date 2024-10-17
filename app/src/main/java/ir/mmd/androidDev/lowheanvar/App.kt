package ir.mmd.androidDev.lowheanvar

import android.app.Application

class App : Application() {
	override fun onCreate() {
		super.onCreate()
		ContentManager = ContentManagerClass(this)
	}
}

package ir.mmd.androidDev.lowheanvar

class Notifier {
	private var notificationListener = {}
	
	@JvmName("kt_notify")
	fun notify() {
		notificationListener()
	}
	
	operator fun invoke(cb: () -> Unit) {
		notificationListener = cb
	}
}

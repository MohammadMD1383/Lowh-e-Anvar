package ir.mmd.androidDev.lowheanvar

class Base26 {
	constructor(value: Int) {
		this.value = value
	}
	
	constructor(value: String) {
		this.value = toInt(value)
	}
	
	private val value: Int
	
	override fun toString() = fromInt(value)
	fun toInt() = value
	
	fun next(): Base26 {
		return Base26(value + 1)
	}
	
	companion object {
		private const val BASE = 26
		private const val START_CHAR = 'a'
		
		fun fromInt(value: Int): String {
			var num = value
			val sb = StringBuilder()
			while (num >= 0) {
				val remainder = num % BASE
				sb.insert(0, (START_CHAR + remainder))
				num = (num / BASE) - 1
			}
			return sb.toString()
		}
		
		fun toInt(value: String): Int {
			var result = 0
			for (char in value) {
				result = result * BASE + (char - START_CHAR)
			}
			return result
		}
	}
}

fun String.toBase26() = Base26(this)

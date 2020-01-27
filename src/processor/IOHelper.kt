package processor

interface IOHelper {
    fun print(msg: String = "")
    fun askLine(msg: String = ""): String
    fun askInt(msg: String = ""): Int
    fun askDouble(msg: String = ""): Double
}

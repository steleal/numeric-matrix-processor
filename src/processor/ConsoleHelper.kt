package processor

import java.util.Scanner

class ConsoleHelper(private val scanner: Scanner) {

    fun print(msg: String = "") = println(msg)

    fun askLine(msg: String = ""): String {
        if (msg != "") this.print(msg)
        return scanner.nextLine()
    }

    fun askInt(msg: String = ""): Int {
        if (msg != "") this.print(msg)
        return scanner.nextInt()
    }

    fun askDouble(msg: String = ""): Double {
        if (msg != "") this.print(msg)
        return scanner.nextDouble()
    }
}

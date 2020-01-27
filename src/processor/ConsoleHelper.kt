package processor

import java.util.Scanner

class ConsoleHelper(private val scanner: Scanner) {

    fun print(msg: String = "") = println(msg)

    fun askLine(msg: String = "") = askForInput(scanner::nextLine, msg)

    fun askInt(msg: String = "") = askForInput(scanner::nextInt, msg)

    fun askDouble(msg: String = "") = askForInput(scanner::nextDouble, msg)

    private fun <T> askForInput(getInput: () -> T, msg: String = ""): T {
        if (msg != "") this.print(msg)
        return getInput()
    }
}

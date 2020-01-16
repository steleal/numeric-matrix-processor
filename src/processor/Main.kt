package processor

import java.util.Scanner

fun main() {
    val helper = ConsoleHelper(Scanner(System.`in`))
    val manager = MatrixManager(helper)
    while (!manager.needExit) {
        manager.printMenu()
        val cmd = manager.askCmd()
        manager.doCmd(cmd)
    }
}

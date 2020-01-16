package processor

class MatrixManager(val helper: ConsoleHelper) {
    var needExit: Boolean = false
        private set

    fun printMenu() {
        helper.print()
        helper.print("1. Add matrices")
        helper.print("2. Multiply matrix to a constant")
        helper.print("3. Multiply matrices")
        helper.print("0. Exit")
    }

    fun doCmd(cmd: String) {
        helper.print()
        when (cmd) {
            "0" -> exit()
            "1" -> addMatrices()
            "2" -> multiplyToConstant()
            "3" -> multiplyMatrices()
            else -> incorrectOption()
        }
    }

    fun askCmd(): String = helper.askLine("Your choice:")

    private fun exit() {
        needExit = true
    }

    private fun addMatrices() {
        val first = inputMatrix("first")
        val second = inputMatrix("second")
        val result = first.add(second)
        helper.print(result.toString())
    }

    private fun multiplyToConstant() {
        val first = inputMatrix("first")
        val constant = helper.askInt("Enter a constant:")
        val result = first.multyTo(constant)
        helper.print(result.toString())
    }

    private fun multiplyMatrices() {
        val first = inputMatrix("first")
        val second = inputMatrix("second")
        val result = first.multyTo(second)
        helper.print(result?.toString() ?: "ERROR")
    }

    private fun incorrectOption() = helper.print("Incorrect option! Try again.")

    private fun inputMatrix(adjective: String = ""): IntMatrix {
        helper.print("Enter size of $adjective matrix:")
        val rows = helper.askInt()
        val columns = helper.askInt()

        helper.print("Enter $adjective matrix:")
        val matrix = IntMatrix(rows, columns)

        for (i in 1..rows) {
            for (j in 1..columns) {
                val value = helper.askInt()
                matrix.set(i, j, value)
            }
        }
        return matrix
    }
}

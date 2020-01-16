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

    private fun exit() {
        needExit = true
    }


    private fun addMatrices() {

    }

    private fun multiplyToConstant() {

    }

    private fun multiplyMatrices() {

    }

    private fun incorrectOption() = helper.print("Incorrect option! Try again.")


}
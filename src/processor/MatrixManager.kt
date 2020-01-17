package processor

import java.text.DecimalFormat

class MatrixManager(val helper: ConsoleHelper) {
    var needExit: Boolean = false
        private set

    fun printMenu() {
        helper.print("""
        |1. Add matrices
        |2. Multiply matrix to a constant
        |3. Multiply matrices
        |4. Transponse matrix
        |5. Calculate a determinant
        |6. Inverse matrix
        |0. Exit
        """.trimMargin())
    }

    fun doCmd(cmd: String) {
        when (cmd) {
            "0" -> exit()
            "1" -> addMatrices()
            "2" -> multiplyToConstant()
            "3" -> multiplyMatrices()
            "4" -> transponseMatrix()
            "5" -> calculateDeterminant()
            "6" -> inverseMatrix()
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
        helper.print("The addition result is:")
        helper.print(result.toString())
    }

    private fun multiplyToConstant() {
        val first = inputMatrix("first")
        val constant = helper.askDouble("Enter a constant:")
        val result = first.multyTo(constant)
        helper.print("The multiplication to constant result is:")
        helper.print(result.toString())
    }

    private fun multiplyMatrices() {
        val first = inputMatrix("first")
        val second = inputMatrix("second")
        val result = first.multyTo(second)
        helper.print("The multiplication result is:")
        helper.print(result.toString())
    }

    private fun transponseMatrix() {
        helper.print("""
        |1. Main diagonal
        |2. Side diagonal
        |3. Vertical line
        |4. Horizontal line          
        """.trimMargin())
        val kindOfTransponse = askCmd()
        val first = inputMatrix("first")

        val result = when (kindOfTransponse) {
            "1" -> first.transponseMain()
            "2" -> first.transponseSide()
            "3" -> first.transponseVertical()
            "4" -> first.transponseHorizontal()
            else -> return
        }

        helper.print("The result is:")
        helper.print(result.toString())
    }

    private fun calculateDeterminant() {
        val first = inputMatrix("first")
        val determinant = first.determinant()
        helper.print("The determinant is:")
        val numberFormat = DecimalFormat("#.##")
        helper.print(numberFormat.format(determinant))
    }

    private fun inverseMatrix() {
        val first = inputMatrix("first")
        val invMatrix = first.inverseMatrix()
        helper.print("The result is:")
        helper.print(invMatrix.toString())
    }

    private fun incorrectOption() = helper.print("Incorrect option! Try again.")

    private fun inputMatrix(adjective: String = ""): Matrix {
        helper.print("Enter size of $adjective matrix:")
        val rows = helper.askInt()
        val columns = helper.askInt()

        helper.print("Enter $adjective matrix:")
        val matrix = Matrix(rows, columns)

        for (i in 1..rows) {
            for (j in 1..columns) {
                val value = helper.askDouble()
                matrix.set(i, j, value)
            }
        }
        helper.askLine() //input hack after scanner.nextInt()
        return matrix
    }
}

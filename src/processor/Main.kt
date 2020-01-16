package processor

import java.util.Scanner

fun main() {
    val scanner= Scanner(System.`in`)
    val matrixA = inputMatrix(scanner)
    val matrixB = inputMatrix(scanner)

    val result = matrixA.add(matrixB)
    println (result ?: "ERROR")
}

fun inputMatrix(scanner: Scanner): IntMatrix {
    val rows = scanner.nextInt()
    val columns = scanner.nextInt()
    val matrix = IntMatrix(rows, columns)

    for (i in 1..rows) {
        for (j in 1..columns) {
            val value = scanner.nextInt()
            matrix.set(i,j, value)
        }
    }
    return matrix
}

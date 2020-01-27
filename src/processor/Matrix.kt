package processor

import java.text.DecimalFormat

class Matrix(
        private val rows: Int,
        private val columns: Int
) {
    private val lastRow = rows - 1
    private val lastColumn = columns - 1
    private val numberFormat = DecimalFormat("#.##")

    private val data = Array(rows) { DoubleArray(columns) }

    fun get(i: Int, j: Int): Double {
        return data[i - 1][j - 1]
    }

    fun set(i: Int, j: Int, value: Double) {
        data[i - 1][j - 1] = value
    }

    private fun sizesEqual(matrix: Matrix): Boolean {
        return rows == matrix.rows &&
                columns == matrix.columns
    }

    fun add(matrix: Matrix): Matrix? {
        if (!this.sizesEqual(matrix)) return null

        val result = Matrix(this.rows, this.columns)

        for (i in 0..lastRow) {
            for (j in 0..lastColumn) {
                result.data[i][j] = this.data[i][j] + matrix.data[i][j]
            }
        }
        return result
    }

    fun multyTo(constant: Double): Matrix {
        val result = Matrix(this.rows, this.columns)

        for (i in 0..lastRow) {
            for (j in 0..lastColumn) {
                result.data[i][j] = this.data[i][j] * constant
            }
        }
        return result
    }

    fun multyTo(matrix: Matrix): Matrix? {
        if (this.columns != matrix.rows) return null
        val result = Matrix(this.rows, matrix.columns)
        for (i in 0..lastRow) {
            for (j in 0..matrix.lastColumn) {
                var dot = 0.0
                for (k in 0..lastColumn) {
                    dot += this.data[i][k] * matrix.data[k][j]
                }
                result.data[i][j] = dot
            }
        }
        return result
    }

    override fun toString(): String {
        return data
                .map { it.map { numberFormat.format(it) }.joinToString(" ") }
                .joinToString(System.lineSeparator())
    }

    fun transponseMain(): Matrix {
        val result = Matrix(columns, rows)
        for (i in 0..lastRow) {
            for (j in 0..lastColumn) {
                result.data[j][i] = data[i][j]
            }
        }
        return result
    }

    fun transponseSide(): Matrix {
        val result = Matrix(columns, rows)
        for (i in 0..lastRow) {
            for (j in 0..lastColumn) {
                result.data[lastColumn - j][lastRow - i] = data[i][j]
            }
        }
        return result
    }

    fun transponseVertical(): Matrix {
        val result = Matrix(columns, rows)
        for (i in 0..lastRow) {
            for (j in 0..lastColumn) {
                result.data[i][lastColumn - j] = data[i][j]
            }
        }
        return result
    }

    fun transponseHorizontal(): Matrix {
        val result = Matrix(columns, rows)
        for (i in 0..lastRow) {
            for (j in 0..lastColumn) {
                result.data[lastRow - i][j] = data[i][j]
            }
        }
        return result
    }

    fun determinant(): Double {
        if (rows == 1) return data[0][0]
        if (rows == 2) {
            return data[0][0] * data[1][1] - data[0][1] * data[1][0]
        }
        var result = 0.0
        for (j in 0..lastColumn) {
            val k = if (j % 2 == 1) -1 else 1
            result += data[0][j] * k * this.minor(0, j)
        }
        return result
    }

    private fun minor(ii: Int, jj: Int): Double {
        val size = rows - 1
        val matrix = Matrix(size, size)
        for (i in 0..lastRow) {
            if (i == ii) continue
            val ni = if (i > ii) i - 1 else i
            for (j in 0..lastColumn) {
                if (j == jj) continue
                val nj = if (j > jj) j - 1 else j
                matrix.data[ni][nj] = data[i][j]
            }
        }
        return matrix.determinant()
    }

    fun inverseMatrix(): Matrix {
        val oneToDetA = 1.0 / this.determinant()
        val transpCofactorMatrix = Matrix(columns, rows)
        for (i in 0..lastRow) {
            for (j in 0..lastColumn) {
                val k = if ((i + j) % 2 == 1) -1 else 1
                transpCofactorMatrix.data[j][i] = k * this.minor(i, j)
            }
        }
        return transpCofactorMatrix.multyTo(oneToDetA)
    }
}

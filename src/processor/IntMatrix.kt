package processor

class IntMatrix(val rows: Int, val columns: Int) {
    private val lastRow = rows - 1
    private val lastColumn = columns - 1

    private val data = Array(rows) { IntArray(columns) }

    fun get(i: Int, j: Int): Int {
        return data[i - 1][j - 1]
    }

    fun set(i: Int, j: Int, value: Int) {
        data[i - 1][j - 1] = value
    }

    fun sizesEqual(matrix: IntMatrix): Boolean {
        return rows == matrix.rows
                && columns == matrix.columns
    }

    fun add(matrix: IntMatrix): IntMatrix? {
        if (!this.sizesEqual(matrix)) return null

        val result = IntMatrix(this.rows, this.columns)

        for (i in 0..lastRow) {
            for (j in 0..lastColumn) {
                result.data[i][j] = this.data[i][j] + matrix.data[i][j]
            }
        }
        return result
    }

    override fun toString(): String {
        return data
                .map { it.joinToString(" ") }
                .joinToString(System.lineSeparator())
    }
}

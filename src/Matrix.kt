import java.lang.IllegalArgumentException

class Matrix(val rows: Int, val columns: Int) {
    private val matrix: Array<Double> = Array(rows * columns) { 0.0 }

    operator fun get(row: Int, column: Int): Double {
        if (row <= rows && column <= columns && row > 0 && column > 0)
            return matrix[(row - 1) * columns + column - 1]
        else
            throw IllegalArgumentException("get[$row, $column] in [$rows, $columns] matrix")
    }

    operator fun set(row: Int, column: Int, number: Double) {
        if (row <= rows && column <= columns && row > 0 && column > 0)
            matrix[(row - 1) * columns + column - 1] = number
        else
            throw IllegalArgumentException("set[$row, $column] in [$rows, $columns] matrix")
    }

    fun det(): Double? {
        return if (rows == columns) {
            // Going with the triangle-shaped (aka Gaussian) determinant calculation method.
            // Thus, copying the whole matrix for the sake of safety
            val changedMatrix = this * 1.0
            for (rowBase in 1 until rows)
                for (rowChange in rowBase+1 until rows+1) {
                    val coeff = -changedMatrix[rowChange, rowBase]/changedMatrix[rowBase,rowBase]
                    for (column in 1..columns)
                        changedMatrix[rowChange,column] += changedMatrix[rowBase,column]*coeff
                }

            var a = 1.0
            for (i in 1..rows)
                a *= changedMatrix[i,i]
            a
        } else
            null
    }

    operator fun plus(b: Matrix): Matrix {
        if (rows == b.rows && columns == b.columns) {
            val c = Matrix(rows, columns)
            for (i in matrix.indices)
                c.matrix[i] = matrix[i] + b.matrix[i]
            return c
        } else {
            throw IllegalArgumentException("Tried to sum matrices of different sizes")
        }
    }

    operator fun minus(b: Matrix): Matrix {
        if (rows == b.rows && columns == b.columns) {
            val c = Matrix(rows, columns)
            for (i in matrix.indices)
                c.matrix[i] = matrix[i] - b.matrix[i]
            return c
        } else {
            throw IllegalArgumentException("Tried to subtract matrices of different sizes")
        }
    }

    operator fun times(b: Matrix): Matrix {
        if (columns == b.rows) {
            val c = Matrix(rows, b.columns)
            for (i in 1..rows)
                for (j in 1..b.columns)
                    for (k in 1..columns) {
                        c[i, j] = c[i, j] + this[i, k] * b[k, j]
                    }
            return c
        } else
            throw IllegalArgumentException("Matrix multiplication error")
    }

    operator fun times(b: Double): Matrix {
        val c = Matrix(rows, columns)
        for (i in matrix.indices)
            c.matrix[i] = matrix[i] * b
        return c
    }

    override fun toString(): String {
        var s = String()
        s += '\n'
        for (i in 1..rows) {
            for (j in 1..columns) {
                s += get(i, j)
                s += ' '
            }
            s += '\n'
        }
        return s
    }
}

operator fun Double.times(b: Matrix): Matrix {
    val c = Matrix(b.rows, b.columns)
    for (i in 1..b.rows)
        for (j in 1..b.columns)
            c[i,j] = b[i,j] * this
    return c
}
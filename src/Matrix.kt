import java.lang.IllegalArgumentException

class Matrix(val rows: Int, val columns: Int) {
    private val matrix: Array<Double> = Array(rows * columns) { 0.0 }

    fun get(row: Int, column: Int): Double {
        if (row <= rows && column <= columns)
            return matrix[(row - 1) * columns + column - 1]
        else
            throw IllegalArgumentException("get[$row, $column]")
    }

    fun set(row: Int, column: Int, number: Double) {
        if (row <= rows && column <= columns)
            matrix[(row - 1) * columns + column - 1] = number
        else
            throw IllegalArgumentException("set[$row, $column]")
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

    operator fun times(b: Double): Matrix {
        val c = Matrix(rows, columns)
        for (i in matrix.indices)
            c.matrix[i] = matrix[i] * b
        return c
    }

    operator fun Double.times(b: Matrix): Matrix {
        val c = Matrix(rows, columns)
        for (i in matrix.indices)
            c.matrix[i] = this * b.matrix[i]
        return c
    }
}
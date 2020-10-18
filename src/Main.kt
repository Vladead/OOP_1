
fun main(args: Array<String>) {
 val a = Matrix(1,3)
 val b = Matrix(3,3)

 a[1, 1] = 1.0
 a[1, 2] = 2.0
 a[1, 3] = 3.0

 b[1, 1] = 1.0
 b[1, 2] = 2.0
 b[1, 3] = 3.0
 b[2, 1] = 4.0
 b[2, 2] = 5.0
 b[2, 3] = 6.0
 b[3, 1] = 7.0
 b[3, 2] = 8.0
 b[3, 3] = 9.0

 println(a * b)
 println(b + b + b - b * 3.0 + 3.0 * b)
 println(b.det())
 println(a == b)
 println(a.rows.toString() + ' ' + a.columns.toString())
}


fun main(args: Array<String>) {
 val a: Matrix = Matrix(1,3)
 val b: Matrix = Matrix(3,3)

 a.set(1,1,1.0)
 a.set(1,2,2.0)
 a.set(1,3,3.0)

 b.set(1,1,1.0)
 b.set(1,2,2.0)
 b.set(1,3,3.0)
 b.set(2,1,4.0)
 b.set(2,2,5.0)
 b.set(2,3,6.0)
 b.set(3,1,7.0)
 b.set(3,2,8.0)
 b.set(3,3,9.0)

 println(a * b)
}
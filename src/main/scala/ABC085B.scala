object ABC085B extends App {

  import java.util.Scanner

  val sc: Scanner    = new java.util.Scanner(System.in)
  val a              = sc.nextInt
  val ints: Seq[Int] = (0 until a).map(_ => sc.nextInt())
  sc.close()
  println(ints.sorted.distinct.size)
}

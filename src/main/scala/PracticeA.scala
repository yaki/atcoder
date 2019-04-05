object PracticeA extends App {
  import java.util.Scanner
  val sc: Scanner = new java.util.Scanner(System.in)
  val a           = sc.nextInt
  val b, c        = sc.nextInt
  val s           = sc.next
  println(s"${a + b + c} $s")
  sc.close()
}
object ABC086A extends App {
  import java.util.Scanner
  val sc: Scanner = new java.util.Scanner(System.in)
  val a, b        = sc.nextInt
  (a * b).toString.last.toInt % 2 match {
    case 0 => println("Even")
    case _ => println("Odd")
  }
  sc.close()
}
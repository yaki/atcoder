object ABC081A extends App {
  import java.util.Scanner
  val sc: Scanner = new java.util.Scanner(System.in)
  val a           = sc.next()
  sc.close()

  private val ans: Int = a.foldLeft(0) {
    case (a, '0') => a
    case (a, _)   => a + 1
  }
  println(ans)
}

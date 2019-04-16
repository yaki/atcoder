object ABC049C extends App {

  import java.util.Scanner

  val sc: Scanner = new java.util.Scanner(System.in)
  val s: String   = sc.next
  sc.close()

  val s1 = "dream"
  val s2 = "dreamer"
  val s3 = "erase"
  val s4 = "eraser"

  val res: Option[String] = Seq(s1, s2, s3, s4).permutations
    .map(_.foldLeft(s)((acc, replS) => {
      acc.replace(replS, "")
    }))
    .find(_ == "")

  res match {
    case Some(_) => println("YES")
    case _       => println("NO")
  }
}

object ABC087B extends App {

  import java.util.Scanner

  val sc: Scanner = new java.util.Scanner(System.in)
  val a, b, c, x  = sc.nextInt
  sc.close()

  val gohyakuYen: Seq[Int] = (0 to a).map(_ * 500)
  val hyakuYen: Seq[Int]   = (0 to b).map(_ * 100)
  val gojyuYen: Seq[Int]   = (0 to c).map(_ * 50)

  private val tuples: Seq[(Int, Int, Int)] = for {
    aa <- gohyakuYen
    bb <- hyakuYen
    cc <- gojyuYen
  } yield (aa, bb, cc)

  println(tuples.map(t => t._1 + t._2 + t._3).count(_ == x))

}

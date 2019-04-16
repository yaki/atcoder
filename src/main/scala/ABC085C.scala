object ABC085C extends App {

  import java.util.Scanner

  val sc: Scanner = new java.util.Scanner(System.in)
  val n, y        = sc.nextInt
  sc.close()
  val ichiman = 10000
  val gosen   = 5000
  val sen     = 1000

  for (i <- 0 to n) {
    val end: Int = n - i
    for (j <- 0 to end) {
      val l = end - j
      if (sum(i, j, l) == y) {
        print(s"$i $j $l")
        sys.exit()
      }
    }
  }

  print("-1 -1 -1")

//  val tuples3: Stream[(Int, Int, Int)] = (0 to n).foldLeft(Stream[(Int, Int, Int)]())((acc, i) => {
//    val end: Int = n - i
//    val tuples: Stream[(Int, Int)] = (0 to end).foldLeft(Stream[(Int, Int)]())((acc2, i2) => {
//      acc2 ++ Stream((i2, end - i2))
//    })
//    tuples.map(t => (i, t._1, t._2)) ++ acc
//  })
//
//  tuples3.filter(t => sum(t) == y).headOption match {
//    case Some(t) => println(s"${t._1} ${t._2} ${t._3}")
//    case _       => println("-1 -1 -1")
//  }

  def sum(t: (Int, Int, Int)): Int =
    t._1 * ichiman + t._2 * gosen + t._3 * sen
}

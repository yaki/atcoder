object ABC083B extends App {

  import java.util.Scanner

  val sc: Scanner = new java.util.Scanner(System.in)
  val n: Int      = sc.nextInt
  val a: Int      = sc.nextInt
  val b: Int      = sc.nextInt
  sc.close()

  val sum = (0 to n)
    .map(convertZeroUme)
    .map(createSumsTuple)
    .filter(isSumTarget(a, b, _))
    .map(_._1.toInt)
    .sum
  println(sum)

  def convertZeroUme(i: Int): String =
    ("0000" + i.toString).takeRight(5)

  def createSumsTuple(s: String): (String, Int) =
    (s, s.toSeq.map(_.asDigit).sum)

  def isSumTarget(a: Int, b: Int, t: (String, Int)): Boolean =
    a <= t._2 && t._2 <= b

}

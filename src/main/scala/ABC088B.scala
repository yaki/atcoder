object ABC088B extends App {

  import java.util.Scanner
  import scala.util.control.Exception._

  val cardE: Either[Throwable, Array[Int]] = allCatch either {
    val sc: Scanner = new java.util.Scanner(System.in)
    val n: Int      = sc.nextLine.toInt
    val s: String   = sc.nextLine
    sc.close()
    s.split(" ").map(_.toInt).sorted.reverse
  }

  cardE.map(calScore) match {
    case Right(score) => println(score._1 - score._2)
    case Left(e)      => e.printStackTrace()
  }

  def calScore(card: Array[Int]): (Int, Int) =
    card.zipWithIndex.foldLeft((0, 0)) {
      case (acc, t) if t._2 % 2 == 0 => (acc._1 + t._1, acc._2)
      case (acc, t) => (acc._1, acc._2 + t._1)
    }
}

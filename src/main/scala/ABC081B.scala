object ABC081B extends App {

  import scala.annotation.tailrec
  import java.util.Scanner
  import cats._
  import cats.data._
  import cats.implicits._

  val sc: Scanner      = new java.util.Scanner(System.in)
  val inputLength      = sc.nextInt()
  val ints: Array[Int] = (0 until inputLength).map(_ => sc.nextInt()).toArray
  sc.close()

  val revBitStringArray: Array[String] = ints.map(_.toBinaryString).map(_.reverse)

//  println(go(ints, 0))
//  println(go2(revBitStringArray, 0))

//  println(Stream.continually(aaa(ints)).takeWhile(a => a._1).length)

  def aaa(ints: Array[Int]): (Boolean, Array[Int]) = {
    ints.foreach(println)
    println()
    ints.exists(isOdd) match {
      case true => (false, ints)
      case _    => (true, allHalf(ints))
    }
  }

  @tailrec
  def go(ints: Array[Int], count: Int): Int =
    ints.exists(isOdd) match {
      case true => count
      case _    => go(allHalf(ints), count + 1)
    }

  @tailrec
  def go2(revBitStringArray: Array[String], count: Int): Int = {
    val tuples: Array[(Boolean, String)] = revBitStringArray.map(isZeroHeadBitAndRemove)

    tuples.map(_._1).exists(!_) match {
      case false => go2(tuples.map(_._2), count + 1)
      case _     => count
    }
  }

  def isZeroHeadBitAndRemove(s: String): (Boolean, String) =
    s.head match {
      case '0' => (true, s.tail)
      case _   => (false, s.tail)
    }

  def allHalf(ints: Array[Int]): Array[Int] =
    ints.map(_ / 2)

  def isOdd(int: Int): Boolean =
    int.toString.last.toInt % 2 match {
      case 0 => false
      case _ => true
    }

  def st: State[Array[Int], Int] = State { initialState =>
    initialState.exists(isOdd) match {
      case true => (initialState, 0)
      case _    => (allHalf(initialState), 1)
    }
  }

  val value: IndexedStateT[Eval, Array[Int], Array[Int], Int] = for {
    _  <- st
    _  <- st
    s3 <- st
  } yield s3

  val stream: Stream[State[Array[Int], Int]] = Stream.continually(st)
  val sequence: State[Array[Int], Stream[Int]] = stream.sequence

  value.run(ints).value._1.foreach(println)

//  st.run(ints).value._1.foreach(println)

//  private[this] def exe(): State[ShoppingContent#Accounts#List, AccountsListResponse] = State { al =>
//    val page = al.execute()
//    al.setPageToken(page.getNextPageToken)
//    (al, page)
//  }
//
//  def getAccountstatuses(mgacs: MstGmcApiConnectSetting): F[Stream[AccountsListResponse]] = F.delay {
//    val accountList: ShoppingContent#Accounts#List = getContent(mgacs)
//      .accounts()
//      .list(BigInteger.valueOf(mgacs.merchantId))
//
//    Stream
//      .continually(exe())
//      .sequence
//      .runA(accountList)
//      .value
//      .takeWhile(_.getNextPageToken != null)
//  }
}

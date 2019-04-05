import cats._
import cats.data._
import cats.implicits._

object Mm extends App {
  type SuicaError[A]    = Either[String, A]
  type SuicaResponse[A] = StateT[SuicaError, Int, A]

  def charge(chargeNum: Int): SuicaResponse[String] =
    for {
      _ <- StateT.modify[SuicaError, Int](_ + chargeNum)
    } yield s"charge: $chargeNum"

  def use(useNum: Int, reason: String): SuicaResponse[String] =
    for {
      balance <- StateT.get[SuicaError, Int]
      _       <- StateT.modify[SuicaError, Int](_ - useNum)
      rest    <- StateT.get[SuicaError, Int]
      _       <- StateT.liftF[SuicaError, Int, String](if (rest >= 0) Right("") else Left(s"use over. balance = [$balance], use = [$useNum]"))
    } yield s"use $reason: useNum$useNum"

  private val value: SuicaResponse[(String, String, String, String, String, String)] = for {
    a   <- charge(10000)
    b   <- use(100, "ガムを買う")
    c   <- use(250, "チキンを買う")
    err <- use(1000000, "車を買う")
    d   <- charge(10000)
    e   <- use(12000, "新幹線に乗る")
    f   <- use(250, "チキンを買う")
  } yield (a, b, c, d, e, f)
  println(value.run(0))
}

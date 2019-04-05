object Main extends App {
  import java.util.Scanner
  import cats.effect.IO

  case class InputData(a: Int, b: Int, c: Int, s: String)

  private val res: Either[Throwable, String] = IO(new java.util.Scanner(System.in))
    .bracket(createAnswer)(scannerClose)
    .attempt
    .unsafeRunSync()

  res match {
    case Right(s) => println(s)
    case Left(e)  => e.printStackTrace()
  }

  def createAnswer(sc: Scanner): IO[String] =
    for {
      input <- getInputData(sc)
      ans   <- createAnswerStr(input)
    } yield ans

  def getInputData(sc: Scanner): IO[InputData] = IO {
    val a    = sc.nextInt
    val b, c = sc.nextInt
    val s    = sc.next
    InputData(a, b, c, s)
  }

  def createAnswerStr(input: InputData): IO[String] = IO {
    s"${input.a + input.b + input.c} ${input.s}"
  }

  def scannerClose(sc: Scanner) = IO {
    sc.close()
  }
}

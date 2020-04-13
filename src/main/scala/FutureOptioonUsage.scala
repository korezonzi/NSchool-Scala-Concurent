import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Random, Success}

object FutureOptioonUsage extends App {
  val random = new Random()
  val waitMaxMillSec = 3000

  val futureMilliSec: Future[Int] = Future {
    val waitMilliSec = random.nextInt(waitMaxMillSec)
    if (waitMilliSec < 1000) throw new RuntimeException(s"waitMilliSec is ${waitMilliSec}")
    Thread.sleep(waitMilliSec)
    waitMilliSec
  }
  //val futureSec: Future[Double] = futureMilliSec.map(x => x.toDouble / 1000)
  val futureSec: Future[Double] = futureMilliSec.flatMap(x => Future{
    Thread.sleep(100)
    x.toDouble/ 1000
  })

  futureSec onComplete{
    case Success(waitSec) => println(s"Sucess! ${waitSec} sec")
    case Failure(t)       => println(s"Failure ${t.getMessage}")
  }
  Thread.sleep(3000)
}

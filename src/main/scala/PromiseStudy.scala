import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.duration._

object PromiseStudy extends App {
  val promiseGetInt: Promise[Int] = Promise[Int]
  val futureByPromise: Future[Int] = promiseGetInt.future //promiseからFutureを作ることができる

  //Promiseが解決された時に実行される処理をFutureを使って書くことが出来る
  val mappedFuture = futureByPromise.map {
    x => println(s"Success! x: ${x}")
  }

  //別スレッドで何か重い処理をして、終わったらPromise に値を渡す
  Future {
    Thread.sleep(300)
    promiseGetInt.success(1)
  }
  Await.ready(mappedFuture, 5000.millisecond)
}

import scala.concurrent._
import scala.concurrent.duration._
import reactivemongo.api.AsyncDriver

object Common {
  val timeout = 5.seconds
  val timeoutMillis = timeout.toMillis.toInt

  lazy val driver = new AsyncDriver()

  lazy val connection = {
    Await.result(driver.connect(List("localhost:27017")), timeout)
  }

  lazy val db = {
    implicit def ec = ExecutionContext.Implicits.global

    Await.result(connection.database("specs2-test-reactivemongo").
      flatMap { _db => _db.drop.map(_ => _db) }, timeout)
  }

  def close(): Unit = try {
    implicit def ec = ExecutionContext.Implicits.global

    Await.result(driver.close(timeout), timeout)
  } catch { case _: Throwable => () }
}

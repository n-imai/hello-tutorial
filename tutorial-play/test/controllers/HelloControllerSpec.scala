package controllers

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.test.{FakeHeaders, FakeRequest}
import play.api.test.Helpers._

class HelloControllerSpec extends PlaySpec with GuiceOneAppPerSuite {

  "An index action" should {
    "return 200" in {
      val Some(hello) = route(app, FakeRequest(
        method = GET,
        path = "/hello",
      ))
      status(hello) mustEqual OK
    }
  }

  "An create action" should {
    "return 200" in {
      val Some(response) = route(app, FakeRequest(
        method = POST,
        uri = "/hello",
        headers = FakeHeaders(Seq("Content-type" -> "application/json")),

        // こんな感じで UserField に合わせて変更
        body = """{ "name": "haco", "companyId": 123 }""",
      ))
      status(response) mustEqual OK

      val json = contentAsJson(response)

      // ここも User#name を確認するように変更
      (json \ "name").as[String] mustEqual "haco"
      // これを追加 : User#location#address を確認
      (json \ "location" \ "address").as[String] mustEqual "紀尾井町ビル"
    }
  }
}

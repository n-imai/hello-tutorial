package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{
  AbstractController,
  AnyContent,
  ControllerComponents,
  Request
}
import japantaxi.tutorial.domain.Greeting
import models.{User, UserField, UserLocation}
import play.api.libs.json.Json

@Singleton
class HelloController @Inject()(cc: ControllerComponents)
    extends AbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    val message = Greeting messageFor "hello-tutorial"
    Ok(message)
  }

  def create() = Action(parse.json) { request =>
    val field = request.body.as[UserField]
    println(field.companyId) // 123
    println(field.name) // haco

    val user = User(
      id = 9999,
      name = field.name,
      companyId = field.companyId,
      location = UserLocation(
        locationId = 1234,
        address = "紀尾井町ビル"
      )
    )
    Ok(Json.toJson(user))

  }
}

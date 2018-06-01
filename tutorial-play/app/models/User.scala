package models

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Writes}

case class User(
    id: Long,
    name: String,
    companyId: Int,
    location: UserLocation,
)

object User {
  implicit val writes: Writes[User] = {
    (JsPath \ "id").write[Long] and
    (JsPath \ "name").write[String] and
    (JsPath \ "companyId").write[Int] and
    (JsPath \ "location").write[UserLocation] apply unlift(User.unapply)
  }
}

case class UserLocation(
    locationId: Long,
    address: String,
)

object UserLocation {
  implicit val writes: Writes[UserLocation] = {
    (JsPath \ "locationId").write[Long] and
    (JsPath \ "address").write[String] apply unlift(UserLocation.unapply)
  }
}

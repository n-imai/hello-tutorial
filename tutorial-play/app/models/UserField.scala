package models

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads}

case class UserField(
    name: String,
    companyId: Int,
)

object UserField {
  implicit val reads: Reads[UserField] = (
    (JsPath \ "name").read[String] and
      (JsPath \ "companyId").read[Int]
  )(UserField.apply _)
}

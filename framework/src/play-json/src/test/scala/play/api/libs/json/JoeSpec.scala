package play.api.libs.json

import org.specs2.mutable._
import play.api.libs.json.Reads._

case class UserProfile(id: Int, name: String, cn: String = "US")

object JoeSpec extends Specification {
  "JoeSpec" should {

    "test reads" in {
      implicit val userReads = JoeJson.readsWithDefault[UserProfile]

      val jsonUser = Json.fromJson[UserProfile](Json.obj("name" -> "toto", "id" -> 45, "cn" -> "US"))
      jsonUser must beEqualTo(JsSuccess(UserProfile(45, "toto")))

      import play.api.libs.functional.syntax._

      val defaultReads = (
        (JsPath \ "id").read[Int] and
        (JsPath \ "name").read[String] and
        ((JsPath \ "cn").read[String] orElse Reads.pure("US"))
      )(UserProfile.apply _)

      val defaultJsonUser = defaultReads.reads(Json.obj("name" -> "toto", "id" -> 45))

      (1 + 1) must beEqualTo(2)
    }
  }
}

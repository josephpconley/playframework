package play.api.libs.json

import org.specs2.mutable._
import play.api.libs.json._
import play.api.libs.json.Json._

case class UserProfile(id: Int, name: String, cn: String = "US")

object JoeSpec extends Specification {

  "readsWithDefault" should {
    "create a readsWithDefault[User]" in {

      implicit val userReads = Json.readsWithDefault[UserProfile]

      val jsonUser = Json.fromJson[UserProfile](Json.obj("name" -> "toto", "id" -> 45))
      jsonUser must beEqualTo(JsSuccess(UserProfile(45, "toto")))
    }
  }
}

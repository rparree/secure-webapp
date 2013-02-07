package core

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{OneInstancePerTest, FlatSpec}
import org.scalatest.selenium.{WebBrowser, Firefox}
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.WebDriver




/**
 * todo
 */
class LoginSpec extends FlatSpec with ShouldMatchers with WebBrowser with OneInstancePerTest {

  val BASE_URL = "http://localhost:8080/secure-webapp"

  type MyDriver = HtmlUnitDriver

  def john  = it
  def jennnifer  = it



  object Fixture{
    def apply(){
      import com.mongodb.casbah.Imports._
      val coll=  MongoClient()("mongodb")("member")
      coll.remove(MongoDBObject.empty)
      coll += MongoDBObject("username"->"jennifer","password"->"masterkey", "roles"->Array("ROLE_MEMBER","ROLE_CASHCOW"))
      coll += MongoDBObject("username"->"john","password"->"masterkey", "roles"->Array("ROLE_MEMBER"))

    }
  }


  private def login(username: String, password: String)(implicit webDriver : WebDriver) {

    textField("username").value = username

    id("password").webElement.sendKeys(password) // M6 will have passwordField()

    click on id("submit")
  }

  "regular member, john" should "have shop access" in {
    Fixture()
    implicit val webDriver = new MyDriver
    go to (BASE_URL + "/shop")
    login("john", "masterkey")
    pageTitle should be("Shop")
    webDriver.close()

  }
  john should "have no access to specials area" in{
    Fixture()
    implicit val webDriver = new MyDriver
    go to (BASE_URL + "/shop/specials/movies.html")
    login("john","masterkey")
    pageTitle should include ("No access")
    webDriver.close()
  }

  "special member, jennifer" should "have access to specials area" in {
    implicit val webDriver = new MyDriver
    Fixture()
    go to (BASE_URL + "/shop/specials/movies.html")
    login("jennifer", "masterkey")
    pageTitle should include("Specials")
    webDriver.close()
  }

  "a visitor" should "need to login to access restricted pages" in {
    Fixture()
    implicit val webDriver = new MyDriver
    go to (BASE_URL + "/shop")
    login("no-user@example.com", "incorrectpassword")
    find("alert-message") match {
      case Some(m: Element) => m.text toLowerCase() should include("failed")
      case _ => fail("expected an alert message")

    }
    webDriver.close()

  }

}



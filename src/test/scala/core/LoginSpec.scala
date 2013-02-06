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

  type MyDriver = FirefoxDriver

  def john  = it
  def jennnifer  = it

  private def login(username: String, password: String)(implicit webDriver : WebDriver) {

    textField("username").value = username

    id("password").webElement.sendKeys(password) // M6 will have passwordField()

    click on id("submit")
  }

  "regular member, john" should "have shop access" in {
    implicit val webDriver = new MyDriver
    go to (BASE_URL + "/shop")
    login("john", "secret")
    pageTitle should be("Shop")
    webDriver.close()

  }
  john should "have no access to specials area" in{
    implicit val webDriver = new MyDriver
    go to (BASE_URL + "/shop/specials/movies.html")
    login("john","secret")
    pageTitle should include ("No access")
    webDriver.close()
  }

  "special member, jennifer" should "have access to specials area" in {
    implicit val webDriver = new MyDriver

    go to (BASE_URL + "/shop/specials/movies.html")
    login("jennifer", "secret")
    pageTitle should include("Specials")
    webDriver.close()
  }

  "a member who logges out" should "have no longer access" in {
    implicit val webDriver = new MyDriver
    go to (BASE_URL + "/shop/specials/movies.html")
    login("jennifer", "secret")
    click on id("logout")
    find("alert-message") match {
      case Some(m: Element) => m.text should include("logged out")
      case _ => fail("expected an alert message")

    }
    go to (BASE_URL + "/shop/specials/movies.html")
    reloadPage() // grrr
    currentUrl should include("login-form.jsp")
    webDriver.close()
  }

  "a visitor" should "need to login to access restricted pages" in {
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

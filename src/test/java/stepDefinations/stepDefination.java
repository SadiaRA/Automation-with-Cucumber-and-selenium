package stepDefinations;

import org.junit.runner.RunWith;
import org.testng.Assert;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import pageObject.LandingPage;
import pageObject.LoginPage;
import pageObject.portalHomePage;
import resources.base;


public class stepDefination extends base {

       

@Given("^Initialize the browser with chrome$")
public void initialize_the_browser_with_chrome() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	driver=InitializeDriver();
}

@Given("^Navigate to \"([^\"]*)\" Site$")
public void navigate_to_Site(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    driver.get(arg1);
}

@Given("^Click on Login link in home page to land on Secure sign in Page$")
public void click_on_Login_link_in_home_page_to_land_on_Secure_sign_in_Page() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
	LandingPage l=new LandingPage(driver);
	if(l.getPopUpSize()>0)

	{

	l.getPopUp().click();

	}
	l.getLogin().click();
}

@When("^User enters (.+) and (.+) and logs in$")
public void user_enters_and_and_logs_in(String username, String password) throws Throwable {
 LoginPage lp=new LoginPage(driver);
	lp.getEmail().sendKeys(username);
	lp.getPassword().sendKeys(password);
    lp.getLogin().click();
}

@Then("^Verify that user is succesfully logged in$")
public void verify_that_user_is_succesfully_logged_in() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    portalHomePage p= new portalHomePage(driver);
    Assert.assertTrue(p.getSearchBox().isDisplayed());
}

@Then("^close browser$")
public void close_browser() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
   driver.quit();
}



}
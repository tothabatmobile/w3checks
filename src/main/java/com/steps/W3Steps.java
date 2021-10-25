package com.steps;

import com.core.driver.DriversFactory;
import com.page.W3PageObject;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.io.IOException;
import java.util.logging.Level;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.testng.Assert;

public class W3Steps {

  private WebDriver webdriver;
  private W3PageObject w3PageObject;

  @Before
  public void before() {
    webdriver = DriversFactory.getWebDriver();
  }

  @After
  public void after() {
    webdriver.quit();
  }

  @Given("^A user navigates to page \"([^\"]*)\"$")
  public void userGoesToPage(String url) {
    w3PageObject = new W3PageObject(webdriver);
    w3PageObject.goToPage(url);
  }

  @Then("user checks for console errors")
  public void userChecksForErrors() {
    LogEntries logs = w3PageObject.getLogs();
    searchForErrorLogs(logs);
  }

  @Then("user checks all contained links are working")
  public void userChecksLinksAreWorking() throws IOException {
    w3PageObject.listOfLinks();
    for (String link : w3PageObject.listOfLinks()) {
      int response = w3PageObject.returnResponseCode(link);
      System.out.println(link);
      Assert.assertTrue(
          response != 200, "Broken links on page" + link);
    }
  }

  @Then("the user checks response is okay")
  public void userChecksTheResponseCode() throws IOException {
    int actualResponseCode = w3PageObject.returnResponseCode();
    int expectedResponseCode = 200;

    Assert.assertEquals(actualResponseCode, expectedResponseCode,
        "Response Code should be 200 but was: " + actualResponseCode);
  }

  private void searchForErrorLogs(LogEntries logs) {
    if (!(logs == null)) {
      logs.forEach((log) -> {
        if (log.getLevel() == Level.SEVERE || log.getLevel() == Level.SEVERE) {
          Assert.fail("Error in console logs:" + log.getMessage());
        }
      });
    }
  }

}

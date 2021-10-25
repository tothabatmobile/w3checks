package com.page;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class W3PageObject {

  protected WebDriver driver;
  protected final static int WAIT_TOLERANCE = 5;

  public W3PageObject(WebDriver webDriver) {
    driver = webDriver;
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, WAIT_TOLERANCE), this);
  }

  @FindBy(tagName = "a")
  private List<WebElement> links;

  public void goToPage(String url) {
    driver.get(url);
  }

  public URL getPageUrl() throws MalformedURLException {
    String stringUrl = driver.getCurrentUrl();
    URL url = new URL(stringUrl);
    return url;
  }

  public LogEntries getLogs() {
    LogEntries logs = null;
    try {
      logs= driver.manage().logs().get(LogType.BROWSER);
      return logs;
    }catch (Exception e) {
      e.printStackTrace();
    }
    return logs;
  }

  public ArrayList<String> listOfLinks() {
    ArrayList<String> listOfLinks = new ArrayList<>();
    int responseCode;
    for (WebElement link : links) {
      String linkValue = link.getAttribute("href");
      if (linkValue == null || linkValue.isEmpty() || linkValue.startsWith("mailto:")) {
//TODO: add something for this logic
      } else {
        try {
          URL url = new URL(linkValue);
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          connection.setRequestMethod("HEAD");
          responseCode = connection.getResponseCode();
        } catch (IOException e) {
          listOfLinks.add(linkValue);
          continue;
        }
        if (responseCode != 200) {
          listOfLinks.add(linkValue);
        }
      }
    }
    return listOfLinks;
  }

  public int returnResponseCode() throws IOException {
    return getResponseCodeForUrl(getPageUrl());
  }

  public int returnResponseCode(String stringUrl) throws IOException {
    URL url = new URL(stringUrl);
    return getResponseCodeForUrl(url);
  }

  private int getResponseCodeForUrl(URL url) throws IOException {
    HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
    httpUrlConnection.setRequestMethod("GET");
    httpUrlConnection.connect();
    return httpUrlConnection.getResponseCode();
  }
}

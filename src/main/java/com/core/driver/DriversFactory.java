package com.core.driver;

import static java.lang.System.getProperty;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriversFactory {

  public static WebDriver getWebDriver() {

    URL grid = null;
    try {
      grid = new URL("tcp://selenium-hub:4442 ");
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    switch (getProperty("browser").toLowerCase()) {
      case "chrome":
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
      case "chrome-docker":
        return new RemoteWebDriver(grid, new ChromeOptions().addArguments("--headless"));
      case "firefox":

        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(getFirefoxOptions());
      case "docker-firefox":
        getFirefoxOptions();
        return new RemoteWebDriver(grid, new FirefoxOptions(getFirefoxOptions()));
    }
    return null;
  }

  private static FirefoxOptions getFirefoxOptions() {
    FirefoxBinary firefoxBinary = new FirefoxBinary();
    FirefoxOptions options = new FirefoxOptions()
        .addPreference("devtools.console.stdout.content", true)
        .setHeadless(true)
        .setBinary(firefoxBinary);
    return options;
  }
}


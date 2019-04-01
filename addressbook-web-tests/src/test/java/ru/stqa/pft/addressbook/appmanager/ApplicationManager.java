package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class ApplicationManager {
  private final Properties properties;
  private WebDriver driver;


  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;


  private boolean acceptNextAlert;
  private StringBuffer verificationErrors = new StringBuffer();
  private String browser;
  private DbHelper dbHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/properties/%s.properties", target))));
    dbHelper = new DbHelper();
    if (browser.equals(BrowserType.FIREFOX)) {
      driver = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      driver = new ChromeDriver();
    } else if (browser.equals(BrowserType.IE)) {
      driver = new InternetExplorerDriver();
    }


    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    driver.get(properties.getProperty("web.baseUrl"));
    groupHelper = new GroupHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    contactHelper = new ContactHelper(driver);
    SessionHelper sessionHelper = new SessionHelper(driver);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
  }

  public void stop() {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public ContactHelper сontact() {
    return contactHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public DbHelper db(){
    return dbHelper;
  }

  public String closeAlertAndGetItsText() throws InterruptedException {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }

  public void setAcceptNextAlert(boolean acceptNextAlert) {
    this.acceptNextAlert = acceptNextAlert;
  }


}

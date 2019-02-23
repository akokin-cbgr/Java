package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.tests.TestBase;
//import org.openqa.selenium.chrome.ChromeDriver;                                                                        //Если надо запускать в браузере Chrome


import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class ApplicationManager {
  WebDriver driver;


  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private SessionHelper sessionHelper;
  private ContactHelper contactHelper;
  private TestBase testBase;

  String baseUrl;
  boolean acceptNextAlert = true;
  StringBuffer verificationErrors = new StringBuffer();

  public void init() {
    //System.setProperty("webdriver.chrome.driver", "/Java_learn/chromedriver/chromedriver.exe");                       //Если надо запускать в браузере Chrome
    //driver = new ChromeDriver();                                                                                      //Если надо запускать в браузере Chrome

    driver = new FirefoxDriver();
    
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    groupHelper = new GroupHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    contactHelper = new ContactHelper(driver);
    sessionHelper = new SessionHelper(driver);
    testBase = new TestBase(driver);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}

package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;


public class TestBase {
  protected WebDriver driver;

  public TestBase(WebDriver driver) {
    this.driver = driver;
  }

  public TestBase() {
  }

  protected final ApplicationManager app = new ApplicationManager();
  private boolean acceptNextAlert;

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

  public boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void setAcceptNextAlert(boolean acceptNextAlert) {
    this.acceptNextAlert = acceptNextAlert;
  }

  public String closeAlertAndGetItsText() {
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

}

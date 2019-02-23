package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.Alert;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;


public class ContactDeletionTest extends TestBase {


  @Test
  public void testContactDeletionTests() throws Exception {
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    //setAcceptNextAlert(true);
    
    Thread.sleep(3000);
    Alert alert = driver.switchTo().alert();
    alert.accept();
    Thread.sleep(40000);
    //app.getContactHelper().returnToHome();


  }


}

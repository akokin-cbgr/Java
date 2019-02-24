package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class ContactDeletionTest extends TestBase {


  @Test
  public void testContactDeletionTests() throws Exception {
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.setAcceptNextAlert(true);
    assertTrue(app.closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    //Thread.sleep(40000);                                                                                              //Задержка в милисекундах
    app.getContactHelper().returnToHome();


  }


}

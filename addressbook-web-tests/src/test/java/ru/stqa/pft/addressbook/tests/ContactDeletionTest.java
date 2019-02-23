package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;


public class ContactDeletionTest extends TestBase {


  @Test
  public void testContactDeletionTests() throws Exception {
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().selectContact();
    //setAcceptNextAlert(true);
    app.getContactHelper().deleteSelectedContact();
    //assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    //app.getContactHelper().confirmationDeleteContact();
    //app.getContactHelper().returnToHome();


  }


}

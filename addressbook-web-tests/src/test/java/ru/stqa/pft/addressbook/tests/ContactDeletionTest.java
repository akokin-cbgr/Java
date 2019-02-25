package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.testng.Assert.assertTrue;


public class ContactDeletionTest extends TestBase {


  @Test
  public void testContactDeletionTests() throws Exception {
    app.getNavigationHelper().gotoContactPage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("test_name","test_middle","test_last","Москва","test@test.com", null, "21","January","1986"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.setAcceptNextAlert(true);
    assertTrue(app.closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    //Thread.sleep(40000);                                                                                              //Задержка в милисекундах
    app.getContactHelper().returnToHome();


  }


}

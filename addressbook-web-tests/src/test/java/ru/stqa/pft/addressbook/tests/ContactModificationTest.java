package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

    @Test
    public void testContactModificationTests() throws Exception {
      app.getNavigationHelper().gotoContactPage();
      if (! app.getContactHelper().isThereAContact()){
        app.getContactHelper().createContact(new ContactData("test_name","test_middle","test_last","Москва","test@test.com", null, "21","January","1986"));
      }
      app.getContactHelper().initContactModification();
      app.getContactHelper().fillContactForm(new ContactData("test_name1","test_middle1","test_last1","Москва1","test@test.com1", null, "22","January","1987"), false);
      app.getContactHelper().submitContactModification();
      app.getContactHelper().returnToHomePage();

    }

}

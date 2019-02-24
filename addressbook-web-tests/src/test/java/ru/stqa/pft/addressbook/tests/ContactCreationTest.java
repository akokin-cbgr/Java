package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreationTests() throws Exception {
      app.getNavigationHelper().gotoContactPage();
      app.getContactHelper().initContactCreation();
      app.getContactHelper().fillContactForm(new ContactData("test_name","test_middle","test_last","Москва","test@test.com", "test1", "21","January","1986"), true);
      app.getContactHelper().submitContactCreation();
      app.getContactHelper().returnToHomePage();



    }





}

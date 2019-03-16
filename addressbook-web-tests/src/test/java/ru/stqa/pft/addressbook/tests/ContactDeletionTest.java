package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;



public class ContactDeletionTest extends TestBase {

  @BeforeTest
  public void ensurePrecondition (){
    app.getNavigationHelper().gotoContactPage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test_name", "test_middle", "test_last", "Москва", "test@test.com", null, "21", "January", "1986"));
    }
  }

  @Test
  public void testContactDeletionTests() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() -1;
    app.getContactHelper().deletionContact(index);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);



  }



}

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;


public class ContactDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePrecondition (){
    app.goTo().сontactPage();
    if (app.сontact().all().size() == 0) {
      app.сontact().create(new ContactData()
              .withFirstname("test_name").withMiddlename("test_middle").withLastname("test_last").withAddress("Москва").withEmail("test@test.com").withBday("21").withBmonth("January").withByear("1986"));
    }
  }

  @Test
  public void testContactDeletionTests() throws Exception {
    Set<ContactData> before = app.сontact().all();
    ContactData deletedContact = before.iterator().next();
    app.сontact().deletionContact(deletedContact);
    Set<ContactData> after = app.сontact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);



  }



}

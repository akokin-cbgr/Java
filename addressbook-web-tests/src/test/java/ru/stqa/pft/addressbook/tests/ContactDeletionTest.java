package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;



public class ContactDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePrecondition (){
    app.goTo().сontactPage();
    if (app.сontact().list().size() == 0) {
      app.сontact().create(new ContactData()
              .withFirstname("test_name").withMiddlename("test_middle").withLastname("test_last").withAddress("Москва").withEmail("test@test.com").withBday("21").withBmonth("January").withByear("1986"));
    }
  }

  @Test
  public void testContactDeletionTests() throws Exception {
    List<ContactData> before = app.сontact().list();
    int index = before.size() -1;
    app.сontact().deletionContact(index);
    List<ContactData> after = app.сontact().list();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);



  }



}

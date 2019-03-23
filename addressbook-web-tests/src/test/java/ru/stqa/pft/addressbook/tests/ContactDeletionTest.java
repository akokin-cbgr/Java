package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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
    Contacts before = app.сontact().all();
    ContactData deletedContact = before.iterator().next();
    app.сontact().deletionContact(deletedContact);
    Contacts after = app.сontact().all();
    assertThat(after.size(), equalTo( before.size() - 1));
    assertThat(after, equalTo(before.without(deletedContact)));



  }



}

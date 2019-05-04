package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePrecondition (){
    app.goTo().сontactPage();
    if (app.db().contacts().size() == 0) {
      File photo = new File("src/test/resources/image_contact/stru.png");

      app.сontact().create(new ContactData()
              .withFirstname("test_name").withMiddlename("test_middle").withLastname("test_last")
              .withAddress("Moscow").withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withEmail("test@test.com").withBday("21").withBmonth("January").withByear("1986").withPhoto(photo));
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.сontact().deletionContact(deletedContact);
    assertEquals(app.сontact().count(),before.size() - 1);

    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));



  }



}

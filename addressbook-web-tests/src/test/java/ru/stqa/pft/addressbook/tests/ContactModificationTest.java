package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertEquals;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().сontactPage();
    if (app.db().contacts().size() == 0) {
      File photo = new File("src/test/resources/image_contact/stru.png");

      app.сontact().create(new ContactData()
              .withFirstname("test_name4").withMiddlename("test_middle4").withLastname("test_last4")
              .withAddress("Moscow")
              .withPhoto(photo)
              .withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withEmail("test@test.com").withBday("26").withBmonth("January").withByear("1987"));
    }
  }

  @Test
  public void testContactModification(){
    File photo = new File("src/test/resources/image_contact/stru.png");
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("test_name4").withMiddlename("test_middle4").withLastname("test_last4")
            .withAddress("Moscow")
            .withPhoto(photo)
            .withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withEmail("test@test.com").withEmail2("").withEmail3("").withBday("26").withBmonth("January").withByear("1987");
    app.сontact().modify(contact);
    assertEquals(app.сontact().count(),before.size());
    Contacts after = app.db().contacts();
    assertThat(after,equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}

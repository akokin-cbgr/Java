package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertEquals;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().сontactPage();
    if (app.сontact().all().size() == 0) {
      app.сontact().create(new ContactData()
              .withFirstname("test_name").withMiddlename("test_middle").withLastname("test_last")
              .withAddress("Moscow").withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withEmail("test@test.com").withBday("21").withBmonth("January").withByear("1986"));
    }
  }

  @Test
  public void testContactModification(){
    Contacts before = app.сontact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("test_name4").withMiddlename("test_middle4").withLastname("test_last4")
            .withAddress("Moscow").withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withEmail("test@test.com").withBday("26").withBmonth("January").withByear("1987");
    app.сontact().modify(contact);
    assertEquals(app.сontact().count(),before.size());
    Contacts after = app.сontact().all();
    assertThat(after,equalTo(before.without(modifiedContact).withAdded(contact)));

  }

}

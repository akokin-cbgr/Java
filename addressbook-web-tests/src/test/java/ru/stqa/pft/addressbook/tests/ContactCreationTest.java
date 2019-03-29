package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase {

  @Test()
  public void testContactCreationTests() throws Exception {
    app.goTo().сontactPage();
    File photo = new File ("src/test/resources/stru.png");
    ContactData contact = new ContactData().withFirstname("test_name").withMiddlename("test_middle").withLastname("test_last")
            .withAddress("Moscow").withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withEmail("test@test.com").withPhoto(photo).withBday("21").withBmonth("January").withByear("1986");
    Contacts before = app.сontact().all();
    app.сontact().create(contact);

    assertEquals(app.сontact().count(),before.size() + 1);
    Contacts after = app.сontact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test()
  public void testBadContactCreationTests() throws Exception {
    app.goTo().сontactPage();
    ContactData contact = new ContactData().withFirstname("test_name'").withMiddlename("test_middle").withLastname("test_last")
            .withAddress("Moscow").withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withEmail("test@test.com").withBday("21").withBmonth("January").withByear("1986");
    Contacts before = app.сontact().all();
    app.сontact().create(contact);
    assertEquals(app.сontact().count(),before.size());
    Contacts after = app.сontact().all();
    assertThat(after, equalTo(before));
  }

}

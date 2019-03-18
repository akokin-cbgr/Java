package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreationTests() throws Exception {
    app.goTo().сontactPage();
    ContactData contact = new ContactData().withFirstname("test_name").withMiddlename("test_middle").withLastname("test_last").withAddress("Москва").withEmail("test@test.com").withBday("21").withBmonth("January").withByear("1986");
    Set<ContactData> before = app.сontact().all();
    app.сontact().create(contact);
    Set<ContactData> after = app.сontact().all();
    Assert.assertEquals(after.size(), before.size() + 1);


    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);

  }

}

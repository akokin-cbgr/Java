package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().сontactPage();
    if (app.сontact().all().size() == 0) {
      app.сontact().create(new ContactData()
                      .withFirstname("test_name").withMiddlename("test_middle").withLastname("test_last").withAddress("Москва").withEmail("test@test.com").withBday("21").withBmonth("January").withByear("1986"));
    }
  }

  @Test
  public void testContactModificationTests(){
    Set<ContactData> before = app.сontact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("test_name4").withMiddlename("test_middle4").withLastname("test_last4").withAddress("Москва").withEmail("test@test.com").withBday("26").withBmonth("January").withByear("1987");
    app.сontact().modify(contact);
    Set<ContactData> after = app.сontact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }


}

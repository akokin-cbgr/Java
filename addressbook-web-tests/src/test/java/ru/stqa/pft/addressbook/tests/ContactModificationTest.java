package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().сontactPage();
    if (app.сontact().list().size() == 0) {
      app.сontact().create(new ContactData()
                      .withFirstname("test_name").withMiddlename("test_middle").withLastname("test_last").withAddress("Москва").withEmail("test@test.com").withBday("21").withBmonth("January").withByear("1986"));
    }
  }

  @Test
  public void testContactModificationTests(){
    List<ContactData> before = app.сontact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData()
            .withId(before.get(index).getId()).withFirstname("test_name4").withMiddlename("test_middle4").withLastname("test_last4").withAddress("Москва").withEmail("test@test.com").withBday("26").withBmonth("January").withByear("1987");
    app.сontact().modify(index, contact);
    List<ContactData> after = app.сontact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}

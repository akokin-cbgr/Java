package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreationTests() throws Exception {
      app.goTo().сontactPage();
      ContactData contact = new ContactData().withFirstname("test_name").withMiddlename("test_middle").withLastname("test_last").withAddress("Москва").withEmail("test@test.com").withBday("21").withBmonth("January").withByear("1986");
      List<ContactData> before = app.сontact().list();
      app.сontact().create(contact);
      List<ContactData> after = app.сontact().list();
      Assert.assertEquals(after.size(), before.size() +1);


      contact.setId(after.stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId());
      before.add(contact);
      Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before, after);

    }

}

package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreationTests() throws Exception {
      app.getNavigationHelper().gotoContactPage();
      ContactData contact = new ContactData("test_name","test_middle","test_last","Москва","test@test.com", null, "21","January","1986");
      List<ContactData> before = app.getContactHelper().getContactList();
      int index = before.size() +1;
      app.getContactHelper().createContact(contact);
      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), index);


      contact.setId(after.stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId());
      before.add(contact);
      Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before, after);

    }

}

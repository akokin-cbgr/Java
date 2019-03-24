package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactViewTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().сontactPage();
    if (app.сontact().all().size() == 0) {
      app.сontact().create(new ContactData()
              .withFirstname("test_name").withMiddlename("test_middle").withLastname("test_last")
              .withAddress("Москва").withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withEmail("test@test.com").withBday("21").withBmonth("January").withByear("1986"));
    }
  }

  @Test()
  public void testContactView() throws Exception {
    app.goTo().сontactPage();
    ContactData contact = app.сontact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.сontact().infoFromEditFrom(contact);
    ContactData contactInfoFromViewForm = app.сontact().infoFromViewFrom(contact);
    assertThat(mergeNames(contactInfoFromEditForm), equalTo(contactInfoFromViewForm.getAllNames()));
    String phonesFromEdit = mergePhones(contactInfoFromEditForm);
    String phonesFromView = mergePhones(contactInfoFromViewForm)
            .replaceAll("null", "").replaceAll("H:","").replaceAll("W:","");
    assertThat(phonesFromEdit, equalTo(phonesFromView));

  }

  private static String mergeNames(ContactData contact) {
    return contact.getFirstname() + " " + contact.getLastname();
  }

  private static String mergePhones(ContactData contact) {
    return contact.getHomePhone() + contact.getMobilePhone() + contact.getWorkPhone();
  }

}

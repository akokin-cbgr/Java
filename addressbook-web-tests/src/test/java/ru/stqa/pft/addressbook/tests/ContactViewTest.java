package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactViewTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().сontactPage();
    if (app.сontact().all().size() == 0) {
      app.сontact().create(new ContactData()
              .withFirstname("test_name").withMiddlename("test_middle").withLastname("test_last")
              .withAddress("Moscow").withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withEmail("test@test.com").withBday("21").withBmonth("January").withByear("1986"));
    }
  }

  @Test()
  public void testContactView() throws Exception {
    app.goTo().сontactPage();
    ContactData contact = app.сontact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.сontact().infoFromEditFrom(contact);
    ContactData contactInfoFromViewForm = app.сontact().infoFromViewFrom(contact);

    String allNamesFromEdit = mergeNames(contactInfoFromEditForm).replaceAll("\\s+"," ").trim();
    String allNamesFromView = contactInfoFromViewForm.getAllNames().replaceAll("\\s+"," ").trim();
    assertThat(allNamesFromEdit, equalTo(allNamesFromView));


    String phonesFromEdit = mergePhones(contactInfoFromEditForm).trim();
    String phonesFromView = mergePhones(contactInfoFromViewForm)
            .replaceAll("null", "").replaceAll("H:","").replaceAll("W:","").replaceAll("M:","").trim();

    assertThat(phonesFromEdit, equalTo(phonesFromView));

    String addressFromEdit = contactInfoFromEditForm.getAddress().trim();
    String addressFromView = contactInfoFromViewForm.getAllAddress().trim();
    assertThat(addressFromEdit, equalTo(addressFromView));

  }

  private static String mergeNames(ContactData contact) {
    return contact.getFirstname()+ " " + contact.getMiddlename()+ " " + contact.getLastname();
  }

  private static String mergePhones(ContactData contact) {
    return contact.getHomePhone() + contact.getMobilePhone() + contact.getWorkPhone();
  }

}

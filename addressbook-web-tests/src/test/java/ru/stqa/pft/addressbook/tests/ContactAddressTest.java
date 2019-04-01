package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddressTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().сontactPage();
    if (app.db().contacts().size() == 0) {
      File photo = new File("src/test/resources/image_contact/stru.png");

      app.сontact().create(new ContactData()
              .withFirstname("test_name").withMiddlename("test_middle").withLastname("test_last")
              .withAddress("Moscow").withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withEmail("test@test.com").withBday("21").withBmonth("January").withByear("1986").withPhoto(photo));
    }
  }

  @Test()
  public void testContactAddress() throws Exception {
    app.goTo().сontactPage();
    ContactData contact = app.db().contacts().iterator().next();
    ContactData contactInfoFromEditForm = app.сontact().infoFromEditFrom(contact);
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }

}

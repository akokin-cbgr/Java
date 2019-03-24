package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactEmailTest extends TestBase {

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
  public void testContactEmail() throws Exception {
    app.goTo().сontactPage();
    ContactData contact = app.сontact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.сontact().infoFromEditFrom(contact);
    String s = mergeEmail(contactInfoFromEditForm);
    assertThat(contact.getEmail(), equalTo(s));
  }

  private static String mergeEmail(ContactData contact) {
    return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
            .stream().filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
  }
}

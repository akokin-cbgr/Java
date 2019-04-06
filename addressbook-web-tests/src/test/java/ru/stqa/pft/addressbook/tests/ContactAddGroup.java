package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;


public class ContactAddGroup extends TestBase{

  @BeforeMethod
  public void ensurePrecondition (){
    app.goTo().сontactPage();
    if (app.db().contacts().size() == 0) {
      File photo = new File("src/test/resources/image_contact/stru.png");

      app.сontact().create(new ContactData()
              .withFirstname("test_name").withMiddlename("test_middle").withLastname("test_last")
              .withAddress("Moscow").withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withEmail("test@test.com").withBday("21").withBmonth("January").withByear("1986").withPhoto(photo));
    }
  }

  @Test
  public void testContactAddGroup() throws Exception {
    Contacts before = app.db().contacts();
    for (ContactData contact: before) {
      Groups groupAll = app.db().groups();
      Groups groupFromContact = contact.getGroups();
      groupAll.removeAll(groupFromContact);

    for (GroupData group:groupAll){
      app.сontact().initAddContactToGroup(group,contact);
    }


    }




  }


}

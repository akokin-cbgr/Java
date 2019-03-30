package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();

  }

  private void run() throws IOException {
    List<ContactData> contacts = generatorContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format");
    }
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    try (Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getMiddlename(), contact.getLastname()
                , contact.getAddress(), contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()
                , contact.getEmail(), contact.getEmail2(), contact.getEmail3()
                , contact.getPhoto(), contact.getBday(), contact.getBmonth(), contact.getByear()));
      }
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xStream = new XStream();
    xStream.processAnnotations(ContactData.class);
    String xml = xStream.toXML(contacts);
    try (Writer writer = new FileWriter(file);) {
      writer.write(xml);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
////    попытка замены пустого значения photo на ссылку файла картинки. Значение в Json сохраняет верное, но все десереализоваться не может
//    int i = json.indexOf(":", json.indexOf("photo\":"));
//    String text = String.valueOf(contacts);
//    String png = "\"" + text.substring(text.indexOf("src\\")).replaceAll("\\}","").replaceAll("\\]","").replaceAll("\\\\","/") + "\"";
//    String jsonNew = json.replaceFirst("\\{\\}", png);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private List<ContactData> generatorContacts(int count) {
    File photo = new File("src/test/resources/image_contact/stru.png");
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstname(String.format("test_name%s", i)).withMiddlename(String.format("test_middle%s", i)).withLastname(String.format("test_last%s", i))
              .withAddress("Moscow").withHomePhone("+7(4722) 186-658").withMobilePhone("22-22-22").withWorkPhone("33 33 33")
              .withEmail(String.format("test1_%s@test.com", i)).withEmail2(String.format("test2_%s@test.com", i)).withEmail3(String.format("test3_%s@test.com", i))
              .withPhoto(photo).withBday("21").withBmonth("January").withByear("1986"));
    }
    return contacts;
  }
}

package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

import static org.testng.Assert.assertTrue;
import static ru.stqa.pft.addressbook.tests.TestBase.app;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver driver) {
    super(driver);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void returnToHome() {
    click(By.linkText("home"));
  }

  public void submitContactCreation() {
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    attache(By.name("photo"), contactData.getPhoto());
    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
//        typeSelect(By.name("new_group"), contactData.getGroup());
        new Select(driver.findElement(By.name("new_group")))
                .selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
    typeSelect(By.name("bday"), contactData.getBday());
    typeSelect(By.name("bmonth"), contactData.getBmonth());
    type(By.name("byear"), contactData.getByear());
  }

  public void initAddContactToGroup(GroupData group, ContactData contact){
    int id = contact.getId();
    click(By.xpath("//*[@id=\"" + id + "\"]"));
    new Select(driver.findElement(By.name("to_group")))
            .selectByVisibleText(group.getName());
    click(By.xpath("//*[@id=\"content\"]/form[2]/div[4]/input"));
    returnToHome();
  }


  public void initDelContactFromGroup(GroupData groupData){
    new Select(driver.findElement(By.name("group")))
            .selectByVisibleText(groupData.getName());
    while (isElementPresent(By.name("selected[]"))) {
      click(By.name("selected[]"));
      click(By.xpath("//*[@id=\"content\"]/form[2]/div[3]/input"));
      returnToHome();
    }
  }


  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  private void selectContactById(int id) {
    driver.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initContactModificationById(int id) {
    //click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='test@test.com'])[1]/following::img[2]"));
    //click(By.xpath("//img[@alt='Edit']"));
    //List<WebElement> elementsEdit = driver.findElements(By.xpath("//table[@id='maintable']/tbody/tr/td[8]"));
    //List<WebElement> elementsId = driver.findElements(By.xpath("//table[@id='maintable']/tbody/tr/td[1]"));
    //driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr/td/a[contains(@href, '" + id + "')]/img")).click();
    //click(By.xpath("//table[@id='maintable']/tbody/tr/td/a[contains(@href, 'edit.php?id="+ id +"')]/img"));
    click(By.xpath("//a[contains(@href, 'edit.php?id=" + id + "')]/img"));

  }

  public void initContactViewById(int id) {
    click(By.xpath("//a[contains(@href, 'view.php?id=" + id + "')]/img"));

  }

  public void submitContactModification() {
    click(By.cssSelector("input:nth-child(86)"));
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();

  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void deletionContact(ContactData contact) throws InterruptedException {
    selectContactById(contact.getId());
    deleteSelectedContact();
    app.setAcceptNextAlert(true);
    assertTrue(app.closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    contactCache = null;
    //Thread.sleep(40000);                                                                                              //Задержка в милисекундах
    returnToHome();

  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return driver.findElements(By.name("selected[]")).size();
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elementsId = driver.findElements(By.xpath("//table[@id='maintable']/tbody/tr/td[1]"));
    List<WebElement> elementsLastName = driver.findElements(By.xpath("//table[@id='maintable']/tbody/tr/td[2]"));
    List<WebElement> elementsFirstName = driver.findElements(By.xpath("//table[@id='maintable']/tbody/tr/td[3]"));
    List<WebElement> elementsAddress = driver.findElements(By.xpath("//table[@id='maintable']/tbody/tr/td[4]"));
    List<WebElement> elementsEmail = driver.findElements(By.xpath("//table[@id='maintable']/tbody/tr/td[5]"));
    List<WebElement> elementsAllPhone = driver.findElements(By.xpath("//table[@id='maintable']/tbody/tr/td[6]"));
    int n = 0;
    for (WebElement elementFirstName : elementsFirstName) {
      String firstNameText = elementFirstName.getText();
      String lastNameText = elementsLastName.get(n).getText();
      String addressText = elementsAddress.get(n).getText();
      String emailText = elementsEmail.get(n).getText();
      String allPhoneText = elementsAllPhone.get(n).getText();
      int id = Integer.parseInt(elementsId.get(n).findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstname(firstNameText).withLastname(lastNameText)
              .withAddress(addressText).withAllPhones(allPhoneText).withEmail(emailText);
      contactCache.add(contact);
      n++;
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditFrom(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
    String middlename = driver.findElement(By.name("middlename")).getAttribute("value");
    String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
    String address = driver.findElement(By.name("address")).getAttribute("value");
    String homePhone = driver.findElement(By.name("home")).getAttribute("value");
    String mobilePhone = driver.findElement(By.name("mobile")).getAttribute("value");
    String workPhone = driver.findElement(By.name("work")).getAttribute("value");
    String email = driver.findElement(By.name("email")).getAttribute("value");
    String email2 = driver.findElement(By.name("email2")).getAttribute("value");
    String email3 = driver.findElement(By.name("email3")).getAttribute("value");
    driver.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withMiddlename(middlename).withLastname(lastname)
            .withAddress(address).withHomePhone(cleaned(homePhone)).withMobilePhone(cleaned(mobilePhone)).withWorkPhone(cleaned(workPhone))
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  public ContactData infoFromViewFrom(ContactData contact) {
    initContactViewById(contact.getId());
    String allNames = driver.findElement(By.xpath("//*[@id=\"content\"]/b")).getText().trim();
    String allText = driver.findElement(By.xpath("//div[@id=\"content\"]")).getText().trim();
//    String[] split = allText.split("\n");
//    String collect = Arrays.stream(split).filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
//    String allNames = split[0];
    String homePhone = "";
    String allAddress = "";
    boolean flag = false;
    if (allText.contains("H:")) {
      homePhone = allText.substring(allText.indexOf("H:"), allText.indexOf("\n", allText.indexOf("H:"))).trim();
      allAddress = allText.substring(allText.indexOf("\n"), allText.indexOf("H:")).trim();
      flag = true;
    }
    String mobilePhone = "";
    if (allText.contains("M:")) {
      mobilePhone = allText.substring(allText.indexOf("M:"), allText.indexOf("\n", allText.indexOf("M:"))).trim();
      if (!flag) {
        allAddress = allText.substring(allText.indexOf("\n"), allText.indexOf("M:")).trim();
        flag = true;
      }
    }
    String workPhone = "";
    if (allText.contains("W:")) {
      workPhone = allText.substring(allText.indexOf("W:"), allText.indexOf("\n", allText.indexOf("W:"))).trim();
      if (!flag) {
        allAddress = allText.substring(allText.indexOf("\n"), allText.indexOf("W:")).trim();
      }
    }
    driver.navigate().back();
    return new ContactData().withId(contact.getId()).withAllNames(allNames).withHomePhone(cleaned(homePhone)).withMobilePhone(cleaned(mobilePhone)).withWorkPhone(cleaned(workPhone)).withAllAddress(allAddress);
  }

}

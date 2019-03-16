package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
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
    type(By.name("email"), contactData.getEmail());
    if (creation) {
      typeSelect(By.name("new_group"), contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
    typeSelect(By.name("bday"), contactData.getBday());
    typeSelect(By.name("bmonth"), contactData.getBmonth());
    type(By.name("byear"), contactData.getByear());
  }


  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectContact(int index) {
    driver.findElements(By.name("selected[]")).get(index).click();
  }

  public void initContactModification(int index) {
    //click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='test@test.com'])[1]/following::img[2]"));
    //click(By.xpath("//img[@alt='Edit']"));
    List<WebElement> elementsEdit = driver.findElements(By.xpath("//table[@id='maintable']/tbody/tr/td[8]"));
    elementsEdit.get(index).click();
  }

  public void submitContactModification() {
    click(By.cssSelector("input:nth-child(86)"));
  }

  public void createContact(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    returnToHomePage();

  }

  public void modifycationContact(int index, ContactData contact) {
    selectContact(index);
    initContactModification(index);
    fillContactForm(contact, false);
    submitContactModification();
    returnToHomePage();
  }


  public void deletionContact(int index) throws InterruptedException {
    selectContact(index);
    deleteSelectedContact();
    app.setAcceptNextAlert(true);
    assertTrue(app.closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    //Thread.sleep(40000);                                                                                              //Задержка в милисекундах
    returnToHome();
  }


  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }


  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elementsId = driver.findElements(By.xpath("//table[@id='maintable']/tbody/tr/td[1]"));
    List<WebElement> elementsLastName = driver.findElements(By.xpath("//table[@id='maintable']/tbody/tr/td[2]"));
    List<WebElement> elementsFirstName = driver.findElements(By.xpath("//table[@id='maintable']/tbody/tr/td[3]"));
    int n = 0;
    for (WebElement elementFirstName : elementsFirstName) {
      String firstNameText = elementFirstName.getText();
      String lastNameText = elementsLastName.get(n).getText();
      int id = Integer.parseInt(elementsId.get(n).findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id, firstNameText, "test_middle", lastNameText, "Москва", "test@test.com", null, "21", "January", "1986");
      contacts.add(contact);
      n++;
    }

    return contacts;
  }
}

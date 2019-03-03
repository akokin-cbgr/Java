package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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
    if (creation){
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

  public void initContactModification() {
    //click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='test@test.com'])[1]/following::img[2]"));
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void createContact(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    returnToHomePage();

  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }


  public List<ContactData> getContactList() {
    List<ContactData> groups = new ArrayList<>();

    WebElement table = driver.findElement(By.id("maintable"));
    List<WebElement> allRows = table.findElements(By.tagName("tr"));
    for (WebElement row : allRows) {
      List<WebElement> cells = row.findElements(By.xpath("td"));
      for (WebElement cell : cells) {
        // And so on
      }
    }




    List<WebElement> elements = driver.findElements(By.cssSelector("td.center"));
    for (WebElement element: elements) {
      String name = element.findElement(By.tagName("input")).getAttribute("title");
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      ContactData group = new ContactData(id,"test_name","test_middle","test_last","Москва","test@test.com", null, "21","January","1986");
      groups.add(group);
    }
    return groups;
  }
}

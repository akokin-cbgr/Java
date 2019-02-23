package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;


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

  public void fillGroupForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    typeSelect(By.name("bday"), contactData.getBday());
    typeSelect(By.name("bmonth"), contactData.getBmonth());
    type(By.name("byear"), contactData.getByear());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void deleteSelectedContact() {
    click(By.cssSelector(".left:nth-child(8) > input"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void initContactModification() {
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='test@test.com'])[1]/following::img[2]"));
  }

  public void submitContactModification() {
    click(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]"));
  }
}

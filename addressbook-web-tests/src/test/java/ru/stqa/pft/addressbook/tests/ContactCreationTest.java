package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreationTests() throws Exception {
      app.getNavigationHelper().gotoContactPage();
      app.getContactHelper().initContactCreation();
      //app.getContactHelper().fillGroupForm(new ContactData("test_name","test_middle","test_last","Москва","test@test.com"));
      //app.getContactHelper().submitContactCreation();
      //app.getContactHelper().returnToHomePage();



/*      driver.findElement(By.name("bday")).click();
      new Select(driver.findElement(By.name("bday"))).selectByVisibleText("21");
      driver.findElement(By.name("bday")).click();
      driver.findElement(By.name("bmonth")).click();
      new Select(driver.findElement(By.name("bmonth"))).selectByVisibleText("January");
      driver.findElement(By.name("bmonth")).click();
      driver.findElement(By.name("byear")).click();
      driver.findElement(By.name("byear")).clear();
      driver.findElement(By.name("byear")).sendKeys("1986");
      //driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]")).click();
      //driver.findElement(By.linkText("home page")).click();




      driver.get("http://localhost/addressbook/");
      driver.findElement(By.linkText("home")).click();
      driver.findElement(By.id("7")).click();
      acceptNextAlert = true;
      driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select all'])[1]/following::input[2]")).click();
      assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
      driver.findElement(By.linkText("home")).click();*/
    }





}

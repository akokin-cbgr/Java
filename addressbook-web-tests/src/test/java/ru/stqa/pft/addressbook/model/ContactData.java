package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String middlename;
  private String lastname;
  private String address;
  private String email;
  private String bday;
  private String bmonth;
  private String byear;
  private String group;

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;

  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;

  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;

  }

  public ContactData withBday(String bday) {
    this.bday = bday;
    return this;

  }

  public ContactData withBmonth(String bmonth) {
    this.bmonth = bmonth;
    return this;

  }

  public ContactData withByear(String byear) {
    this.byear = byear;
    return this;

  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;

  }

  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getBday() {
    return bday;
  }

  public String getBmonth() {
    return bmonth;
  }

  public String getByear() {
    return byear;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (!Objects.equals(firstname, that.firstname)) return false;
    return Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }
}

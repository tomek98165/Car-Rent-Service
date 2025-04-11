package com.carrentservice.domain.crud.util;

import jakarta.persistence.*;

import java.util.Date;

@Entity
class Customer {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   @Column(nullable = false)
   private String name;
   @Column(nullable = false)
   private String lastName;
   private String address;



   private String zipCode;
   private String town;
   @Column(unique = true, nullable = false)
   private String pesel;
   private Date birthDate;
   @Enumerated(EnumType.STRING)
   private Gender Gender;
   @Column(unique = true, length = 9, nullable = false)
   private String phoneNumber;

   @Column(unique = true)
   private String email;

   public Customer() {
   }


   public Customer(String name, String lastName, String address, String zipCode, String town, String pesel, Date birthDate, com.carrentservice.domain.crud.util.Gender gender, String phoneNumber, String email) {
      this.name = name;
      this.lastName = lastName;
      this.address = address;
      this.zipCode = zipCode;
      this.town = town;
      this.pesel = pesel;
      this.birthDate = birthDate;
      Gender = gender;
      this.phoneNumber = phoneNumber;
      this.email = email;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getZipCode() {
      return zipCode;
   }

   public void setZipCode(String zipCode) {
      this.zipCode = zipCode;
   }

   public String getTown() {
      return town;
   }

   public void setTown(String town) {
      this.town = town;
   }

   public String getPesel() {
      return pesel;
   }

   public void setPesel(String pesel) {
      this.pesel = pesel;
   }

   public Date getBirthDate() {
      return birthDate;
   }

   public void setBirthDate(Date birthDate) {
      this.birthDate = birthDate;
   }

   public com.carrentservice.domain.crud.util.Gender getGender() {
      return Gender;
   }

   public void setGender(com.carrentservice.domain.crud.util.Gender gender) {
      Gender = gender;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
}

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

   public Customer(Long id, String name, String lastName, String address, String zipCode, String town, String pesel, Date birthDate, com.carrentservice.domain.crud.util.Gender gender, String phoneNumber, String email) {
      this.id = id;
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

   Customer(String name, String lastName, String address, String zipCode, String town, String pesel, Date birthDate, com.carrentservice.domain.crud.util.Gender gender, String phoneNumber, String email) {
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

   protected Customer() {

   }

   Long getId() {
      return id;
   }
   void setId(Long id) {
      this.id = id;
   }

   String getName() {
      return name;
   }

   void setName(String name) {
      this.name = name;
   }

   String getLastName() {
      return lastName;
   }

   void setLastName(String lastName) {
      this.lastName = lastName;
   }

   String getAddress() {
      return address;
   }

   void setAddress(String address) {
      this.address = address;
   }

   String getZipCode() {
      return zipCode;
   }

   void setZipCode(String zipCode) {
      this.zipCode = zipCode;
   }

   String getTown() {
      return town;
   }

   void setTown(String town) {
      this.town = town;
   }

   String getPesel() {
      return pesel;
   }

   void setPesel(String pesel) {
      this.pesel = pesel;
   }

   Date getBirthDate() {
      return birthDate;
   }

   void setBirthDate(Date birthDate) {
      this.birthDate = birthDate;
   }

   com.carrentservice.domain.crud.util.Gender getGender() {
      return Gender;
   }

   void setGender(com.carrentservice.domain.crud.util.Gender gender) {
      Gender = gender;
   }

   String getPhoneNumber() {
      return phoneNumber;
   }

   void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   String getEmail() {
      return email;
   }

   void setEmail(String email) {
      this.email = email;
   }
}

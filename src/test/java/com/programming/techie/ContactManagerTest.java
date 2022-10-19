package com.programming.techie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;

class ContactManagerTest {
  public    ContactManager contactManager ;
    @BeforeAll
    public static void setupAll() {
        System.out.println("Instantiating Contact Manager before the Test Execution");

    }
    @BeforeEach
    public void setup(){
        contactManager = new ContactManager();
    }
    @Test
    @DisplayName("Should create contact")
    @EnabledOnOs(value =  OS.MAC,disabledReason = "Should run only on MAC!")
    public void shouldCreateContact(){

        contactManager.addContact("John","Doe","0988885456");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1,contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .filter(contact -> contact.getFirstName().equals("John") &&
                        contact.getLastName().equals("Doe") &&
                        contact.getPhoneNumber().equals("0988885456"))
                .findAny()
                .isPresent());
    }
    @Test
    @DisplayName("Should not create contact when First name is null")
    @EnabledOnOs(value = OS.WINDOWS, disabledReason = "Should run only on Windows")
    public void shouldThrowRunTimeExceptionWhenFNameIsNull(){

        Assertions.assertThrows(RuntimeException.class,()->{
            contactManager.addContact(null,"Doe","0988885456");
        });
    }
    @Test
    @DisplayName("Should not create contact when Last name is null")
    public void shouldThrowRunTimeExceptionWhenLNameIsNull(){

        Assertions.assertThrows(RuntimeException.class,()->{
            contactManager.addContact("John",null,"0988885456");
        });
    }
    @Test
    @DisplayName("Should not create contact when PhoneNumber is null")
    public void shouldThrowRunTimeExceptionWhenPhoneNumberIsNull(){

        Assertions.assertThrows(RuntimeException.class,()->{
            contactManager.addContact("John","Doe",null);
        });
    }
    @Test
    @DisplayName("Test Contact Creation on Developer Machine")
    public void shouldTestContactCreationOnDEV() {
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        contactManager.addContact("John", "Doe", "0988885456");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }
    @AfterEach
    public void tearDown() {
        System.out.println("Should Execute After Each Test");
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("Should be executed at the end of the Test");
    }

}
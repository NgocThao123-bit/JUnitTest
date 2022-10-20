package com.programming.techie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

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
    @DisplayName("Repeat Contact Creation Test 5 Times")
    @RepeatedTest(5)
    public void shouldTestContactCreationRepeatedly() {
        contactManager.addContact("John", "Doe", "0988885456");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }
//    @Test
//    @DisplayName("Phone Number should start with 0")
//    public void shouldTestPhoneNumberFormat() {
//        contactManager.addContact("John", "Doe", "0988885456");
//        assertFalse(contactManager.getAllContacts().isEmpty());
//        assertEquals(1, contactManager.getAllContacts().size());
//    }

//    @DisplayName("Phone Number should match the required Format")
//    @ParameterizedTest
//    @ValueSource(strings = {"0988885456", "988885456", "+0988885456"})
//    public void shouldTestPhoneNumberFormat(String phoneNumber) {
//        contactManager.addContact("John", "Doe", phoneNumber);
//        assertFalse(contactManager.getAllContacts().isEmpty());
//        assertEquals(1, contactManager.getAllContacts().size());
//    }
//    @DisplayName("Method Source Case - Phone Number should match the required Format")
//    @ParameterizedTest
//    @MethodSource("phoneNumberList")
//    public void shouldTestPhoneNumberFormatUsingMethodSource(String phoneNumber) {
//        contactManager.addContact("John", "Doe", phoneNumber);
//        assertFalse(contactManager.getAllContacts().isEmpty());
//        assertEquals(1, contactManager.getAllContacts().size());
//    }
//
//    private static List<String> phoneNumberList() {
//        return Arrays.asList("0123456789", "01234567890", "0123456789");
//    }
    @DisplayName("CSV Source Case - Phone Number should match the required Format")
    @ParameterizedTest
    @CsvSource({"0123456789", "2,1234567890","3,+0123456789"})
    public void shouldTestPhoneNumberFormatUsingCSVSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
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
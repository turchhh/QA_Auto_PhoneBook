package ui_tests;

import dto.Contact;
import dto.ContactsDto;
import dto.User;
import io.restassured.response.Response;
import manager.ApplicationManager;
import manager.ContactController;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.HeaderMenuItem;

import static pages.BasePage.clickButtonHeader;
import static utils.PropertiesReader.getProperty;
import static utils.RandomUtils.generateEmail;
import static utils.RandomUtils.generateString;

public class AddNewContactTestWithApi extends ApplicationManager {
    HomePage homePage;
    LoginPage loginPage;
    ContactsPage contactsPage;
    AddPage addPage;

    @BeforeMethod
    public void login() {
        User user = new User(getProperty("login.properties", "email"),
                getProperty("login.properties", "password"));
        homePage = new HomePage(getDriver());
        loginPage = clickButtonHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginForm(user);
        contactsPage = new ContactsPage(getDriver());
        addPage = clickButtonHeader(HeaderMenuItem.ADD);
    }

    @Test()
    public void addNewContactPositiveTest() {
        Contact contact = Contact.builder()
                .name("123"+generateString(5))
                .lastName(generateString(10))
                .phone("0123456789")
                .email(generateEmail(10))
                .address("Haifa " + generateString(10))
                .description("desc " + generateString(15))
                .build();
        addPage.typeAddNewContactForm(contact);
        BasePage.pause(10);
        ContactController contactController = new ContactController();
        contactController.login();
        Response response = contactController.getAllUserContacts();
        System.out.println(response.getStatusLine());
        ContactsDto contactsDto = new ContactsDto();
        if(response.getStatusCode()==200){
            contactsDto = response.body().as(ContactsDto.class);
        }
        for (Contact contact1: contactsDto.getContacts()){
            if(contact1.equals(contact)){
                System.out.println(contact1);
                System.out.println(contact);
                //Assert.assertEquals(contact1, contact);
            }
        }

    }
}
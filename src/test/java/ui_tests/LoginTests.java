package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.TestNGListener;

import java.lang.reflect.Method;

@Listeners(TestNGListener.class)


public class LoginTests extends ApplicationManager {

    @Test
    public void loginPositiveTest(Method method){
        logger.info("start method {}", method.getName());
        User user = new User("qa_mail@mail.com", "Qwerty123!");
        logger.info("test data {}", user.toString());
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        ContactsPage contactsPage = new ContactsPage(getDriver());
        Assert.assertTrue(contactsPage.isContactsPresent());
        //System.out.println("after assert");
    }

    @Test
    public void loginNegativeTest_wrongPassword(){
        User user = new User("qa_mail@mail.com", "Qwerty123!ZZZ");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.closeAlert();
        Assert.assertTrue(loginPage.isErrorMessagePresent("Login Failed with code 401"));
    }

    @Test
    public void loginNegativeTest_wrongEmail(){
        User user = new User("qa_mailmail.com", "Qwerty123!");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.closeAlert();
        Assert.assertTrue(loginPage.isErrorMessagePresent("Login Failed with code 401"));
    }
}
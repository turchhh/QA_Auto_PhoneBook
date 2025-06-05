package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends ApplicationManager {
    @Test
    public void loginPositiveTest(){
        User user = new User("vturchannikova@gmail.com","678512Lera!");
        HomePage homePage = new HomePage(gerDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(gerDriver());
        loginPage.typeLoginForm(user);
        ContactsPage contactsPage = new ContactsPage(gerDriver());
        Assert.assertTrue(contactsPage.isContactsPresent());
//        System.out.println("after assert");

    }

    @Test
    public void loginNegativeTest_wrongPassword() {
        User user = new User("vturchannikova@gmail.com", "678512Lera!ZZZ");
        HomePage homePage = new HomePage(gerDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(gerDriver());
        loginPage.typeLoginForm(user);
        loginPage.closeAlert();
        Assert.assertTrue(loginPage.isErrorMessagePresent("Login Failed with code 401"));
    }
}

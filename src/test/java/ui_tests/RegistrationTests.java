package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.RetryAnalyzer;
import utils.TestNGListener;

import static utils.RandomUtils.*;
@Listeners(TestNGListener.class)
public class RegistrationTests extends ApplicationManager {
    HomePage homePage;
    LoginPage loginPage;
    @BeforeMethod(alwaysRun = true)
    public void goToRegistrationPage(){
        homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        loginPage = new LoginPage(getDriver());
    }
    //      BeforeMethod(App)   BeforeMethod(Reg)  Test  AfterMethod(App)

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    public void registrationPositiveTest(){
        User user = new User(generateEmail(10), "Password123!");
        loginPage.typeRegistrationForm(user);
        Assert.assertTrue(loginPage.isNoContactMessagePresent("Add new by clicking on Add in NavBar!"));
    }

    @Test
    public void registrationNegativeTest_duplicateUser(){
        User user = new User(generateEmail(10), "Password123!");
        loginPage.typeRegistrationForm(user);
        if(loginPage.isNoContactMessagePresent("Add new by clicking on Add in NavBar!")){
            loginPage.logOut();
            loginPage.typeRegistrationForm(user);
            Assert.assertTrue(loginPage.closeAlertReturnText()
                    .contains("User already exist"));
        }else {
            Assert.fail("wrong registration with user " + user.toString());
        }
    }

    @Test
    public void registrationNegativeTest_wrongPassword(){
        User user = new User(generateEmail(10), "Password123");
        loginPage.typeRegistrationForm(user);
        Assert.assertTrue(loginPage.closeAlertReturnText()
                .contains("Password must contain at least one special symbol from [‘$’,’~’,’-‘,’_’]!"));
    }
}
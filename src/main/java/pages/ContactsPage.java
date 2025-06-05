package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ContactsPage extends BasePage {
    public ContactsPage(WebDriver driver) {
        serDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//a[@href='/contacts']")
    WebElement btnContactHeader;

    public boolean isContactsPresent() {
        return isElementPresent(btnContactHeader);
    }
}

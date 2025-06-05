package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {
    static WebDriver driver;
    public static void serDriver(WebDriver wd){
        driver = wd;
    }

    public void pause(int time) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isElementPresent(WebElement element){
        return element.isDisplayed();
    }

    public boolean isElementPresent(WebElement element, String text){
        return element.getText().contains(text);
    }

    public boolean isTextInElementPresent(WebElement element, String text){
        return element.getText().contains(text);
    }
}

package Core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class BaseClass {

    protected WebDriver driver;
    protected WebDriverWait wait;
    int time = 15;

    public BaseClass(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, time);
    }

    //@Step("Wait clickability of element")
    public void waitClickability(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    //@Step("Wait visibility of element")
    public void waitVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    //@Step("Wait page for load")
    public void waitForPageLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    //----------------------------------------WINDOWS ACTIONS-----------------------------------------------------------
    //@Step("{step}")
    public static void openNewWindow(String step, WebDriver driver, String homePage) {
        Set<String> window = driver.getWindowHandles();
        Iterator iterator = window.iterator();
        String currentWindowId = "WIndow Not found";
        while (iterator.hasNext()) {
            currentWindowId = iterator.next().toString();

            if (!currentWindowId.equals(homePage)) driver.switchTo().window(currentWindowId);
        }
    }

    //@Step("{step}")
    public void switchTabs(String step, WebDriver driver, int tabNumber) {
        try {
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(tabNumber));
        } catch (Exception e) {
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        }
    }

    //@Step("{step}")
    public void closeTab(String step, WebDriver driver, int tabNumber) {
        try {
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(tabNumber));
            driver.close();
        } catch (Exception e) {
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        }
    }

    //@Step("{step}")
    public void switchToForm(String step, WebElement element){
        driver.switchTo().frame(element);
    }

    //---------------------------------------------RANDOMIZER-----------------------------------------------------------

    //@Step("{step}")
    public static String random(String step, int min, int max) {
        int randomInt = (int) ((Math.random() * ((max - min) + 1)) + min);
        String numberString = Integer.toString(randomInt);
        return numberString;
    }
}

package Utilities;

import Core.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.testng.Assert.fail;

public class Actions extends BaseClass {
    public Actions(WebDriver driver) {
        super(driver);
    }

    @Step("{step}")
    public void inputData(String step, WebElement element, String data) {
        try {
            waitVisibility(element);
            element.clear();
            element.sendKeys(data);
        }catch (WebDriverException e){
            fail("Element is not visible");
        }
    }

    //----------------------------------------------SELECTIONS----------------------------------------------------------

    @Step("{step}")
    public void selectItemByValue(String step, WebElement element, String value) {
        Select dropdown = new Select(element);
        dropdown.selectByValue(value);
    }

    @Step("{step}")
    public void selectItemByIndex(String step, WebElement element, int index) {
        Select input = new Select(element);
        input.selectByIndex(index);
    }

    @Step("{step}")
    public void selectSpecificValueInDropDown(String step, WebElement element, String specificValue) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(specificValue);
    }


    @Step("{step}")
    public void selectRandomValueInDropDown(String step, WebElement element) {
        Select selectElement = new Select(element);
        List<WebElement> validSelectOptions = selectElement
                .getOptions()
                .stream()
                .filter(p -> !p.getAttribute("value").equals(""))
                .collect(Collectors.toList());
        Random rand = new Random();
        WebElement chosenElement = validSelectOptions.get(rand.nextInt(validSelectOptions.size()));
        selectElement.selectByValue(chosenElement.getAttribute("value"));
    }

    //------------------------------------------------CLICKS------------------------------------------------------------

    @Step("{step}")
    public void waitAndClick(String step, WebElement element) {
        try {
            waitVisibility(element);
            waitClickability(element);
            element.click();
        }catch (ElementClickInterceptedException e){
            driver.navigate().refresh();
            element.click();
        } catch (WebDriverException wb) {
            fail("Element is not clickable");
        }
    }

    @Step("{step}")
    public void clickOnElementIfContainsText(String step, WebElement element, String expectedText) {
        waitVisibility(element);
        if (element.getText().contains(expectedText)) {
            element.click();
        } else {
            System.out.println("Element doesn't contain text");
        }
    }

    @Step("{step}")
    public void clickIfValueIsTrue(String step, WebElement element) {
        String value = element.getAttribute("value");
        try {
            if (value.contains("1")) {
                waitClickability(element);
                waitVisibility(element);
                element.click();
            } else {
                System.out.println("CheckBox are clear");
            }
        } catch (Exception e) {
        }
    }

    @Step("{step}")
    public void clickIfValueIsFalse(String step, WebElement element) {
        System.out.println("try select checkBox");
        String value = element.getAttribute("value");
        try {
            if (value.contains("1")) {
                System.out.println("CheckBox already selected");
            } else {
                waitClickability(element);
                waitVisibility(element);
                element.click();
                System.out.println("checkBox - successfully selected");
            }
        } catch (Exception e) {
        }
    }


    @Step("{step}")
    public void waitAndMoveAndClick(String step, WebElement element) {
        try {
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(element);
            actions.perform();
            waitVisibility(element);
            waitClickability(element);
            actions.moveToElement(element);
            actions.perform();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            System.out.println("Try : " + step);
            element.click();
        } catch (StaleElementReferenceException ex) {
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(element);
            actions.perform();
            element.click();
        }
    }


    //-------------------------------------------MOUSE MOVEMENTS--------------------------------------------------------

    @Step("{step}")
    public void mouseOver(String step, WebElement element){
        waitClickability(element);
        org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(driver);
        builder.moveToElement(element).build().perform();
    }

    @Step("{step}")
    public void moveToElement(String step, WebElement element) {
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        actions.moveToElement(element);
        //actions.moveToElement(element);
        actions.perform();
    }

    //------------------------------------------------HELPERS-----------------------------------------------------------

    @Step("{step}")
    public void acceptAlert(String step) {
        for (int i = 0; ; i++) {
            if (i >= 5) {

            } else {
                try {
                    {
                        Thread.sleep(2500);
                        Alert alert = driver.switchTo().alert();
                        String text = alert.getText();
                        alert.accept();
                        System.out.println("'" + text + "' - Alert present");
                        break;
                    }
                } catch (Exception e) {
                    break;
                }
            }
        }
    }


}

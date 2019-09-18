package Utilities;

import Core.BaseClass;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class Asserts extends BaseClass {
    public Asserts(WebDriver driver) {
        super(driver);
    }

    @Step("{step}")
    public void assertURL(String step, String expectedURL) {
        try {
            assertTrue(wait.until(ExpectedConditions.urlToBe(expectedURL)));
        }catch (TimeoutException e){
            fail("Url not equals: " + expectedURL);
        }
    }


    @Step("{step}")
    public void containsURL(String step, String expectedURL) {
        try {
            assertTrue(wait.until(ExpectedConditions.urlContains(expectedURL)));
        }catch (TimeoutException e){
            fail("Url not contains: " + expectedURL);
        }
    }

    //------------------------------------------------TEXT--------------------------------------------------------------

    @Step("{step}")
    public void assertTextPresentAtPage(String step, String expectedText) {
        try {
            assertTrue(wait.until(new ExpectedCondition<Boolean>() {
                @Nullable
                @Override
                public Boolean apply(@Nullable WebDriver driver) {
                    return driver.getPageSource().contains(expectedText);
                }
            }));
        }catch (TimeoutException e){
            fail("Text not present at page: " + expectedText);
        }
    }


    @Step("{step}")
    public void assertTextAbsentAtPage(String step, String expectedText) {
        try {
            assertFalse(wait.until(new ExpectedCondition<Boolean>() {
                @Nullable
                @Override
                public Boolean apply(@Nullable WebDriver driver) {
                    return driver.getPageSource().contains(expectedText);
                }
            }));
        }catch (TimeoutException e){
            fail("Text not absent at page");
        }
    }

    @Step("{step}")
    public void assertElementContainsText(String step, WebElement element, String expectedText) {
        try {
            assertTrue(wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText)));
        }catch (TimeoutException e){
            fail("Text not present in element");
        }
    }


    //----------------------------------------------ELEMENTS------------------------------------------------------------

    @Step("{step}")
    public void assertElementPresent(String step, WebElement element) {
        try {
            assertTrue(wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed());
        }catch (TimeoutException e){
            fail("Element not found");
        }
    }



    //-----------------------------------------------TITLE--------------------------------------------------------------

    @Step("{step}")
    public void assertPageTitle(String step, String text) {
        try {
            assertTrue(wait.until(ExpectedConditions.titleIs(text)));
        }catch (TimeoutException e){
            fail("Title is not correct");
        }
    }

    //----------------------------------------------COUNTERS------------------------------------------------------------

    @Step("{step}")
    public void assertNumberOfElementsIncreased(String step, int haveElements, int increasedElements) {

        System.out.println("Try to assert -" + haveElements + " change To - " + increasedElements);
        if (haveElements > increasedElements) {
            fail("FAIL: " + haveElements + "Don`t have " + increasedElements + " elements");
        } else {
        }
    }


    @Step("{step}")
    public void countNumberOfWebElements(String step, List<WebElement> elementsList, int requiredNumber) {
        int amount = elementsList.size();
        if (amount == requiredNumber) {
            System.out.println("Items on page are equal = " + requiredNumber);
        } else {
            fail("FAIL: Number of elements on the page" + amount);
            System.out.println("FAIL: Items on the page do not match the desired value = " + requiredNumber);
            System.out.println("FAIL: Number of elements on the page" + amount);
        }
    }

    @Step("{step}")
    public boolean assertOneOfArrayElementPresent(String step, WebElement element1, WebElement element2) {
        List<WebElement> webElementList = new ArrayList<>();
        webElementList.add(element1);
        webElementList.add(element2);

        boolean isDisplayed = true;

        for(WebElement element : webElementList){

            try{
                new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOf(element));
                isDisplayed = true;
                break;
            }catch (WebDriverException we){
                isDisplayed = false;
                continue;
            }
        }
        return isDisplayed;

    }
}

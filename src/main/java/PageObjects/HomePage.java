package PageObjects;

import Core.BaseClass;
import Utilities.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseClass {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    Actions actions = new Actions(driver);

    @FindBy(xpath = "//button[text() = 'Log In']")
    private WebElement loginButton;
    @FindBy(xpath = "//input[@name = 'email']")
    private WebElement emailField;
    @FindBy(xpath = "//input[@name = 'password']")
    private WebElement passwordField;
    @FindBy(xpath = "//button[contains(@class, 'auth-btn')]")
    private WebElement loginButtonAtAuth;
    @FindBy(xpath = "//header//a[text() = 'About Us']")
    private WebElement aboutUsLink;

    public WebElement getAboutUsLink() {
        return aboutUsLink;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getLoginButtonAtAuth() {
        return loginButtonAtAuth;
    }

    public void clickOnElementAtHomePage(String step, WebElement element){
        actions.waitAndClick(step, element);
    }

    public void inputDataAtHomePage(String step, WebElement element, String data){
        actions.inputData(step, element, data);}


}

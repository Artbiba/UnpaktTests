import Core.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestListenerAdapter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListenerAdapter.class)
@Epic("UnpaktTests")
@Feature("HomeTests")
public class TestClass extends BaseTest {

    @Test
    @Description("Test case 01: Login with invalid values")
    public void test01() {
        homePage.clickOnElementAtHomePage("Click on 'Log in' button", homePage.getLoginButton());
        homePage.inputDataAtHomePage("Input invalid email", homePage.getEmailField(), "test2@gmail.com");
        homePage.inputDataAtHomePage("Input invalid password", homePage.getPasswordField(), "qwerty123");
        homePage.clickOnElementAtHomePage("Click on auth 'Log in' button", homePage.getLoginButtonAtAuth());
        asserts.assertTextPresentAtPage("Assert that login error text present", "Incorrect username or password");
    }

    @Test
    @Description("Test case 02: Login without values")
    public void test02() {
        homePage.clickOnElementAtHomePage("Click on 'Log in' button", homePage.getLoginButton());
        homePage.clickOnElementAtHomePage("Click on auth 'Log in' button", homePage.getLoginButtonAtAuth());
        asserts.assertTextPresentAtPage("Assert that login error text present", "Email is required");
        asserts.assertTextPresentAtPage("Assert that login error text present", "Password is required");
    }

    @Test
    @Description("Test case 03: Go to 'About Us' page")
    public void test03() {
        homePage.clickOnElementAtHomePage("Click on 'About Us' link", homePage.getAboutUsLink());
        asserts.assertURL("Assert that url correct", "https://exp.unpakt.com/what-is-unpakt/");
    }

    @Test
    @Description("Test case 04: Login with valid values")
    public void test04() {
        homePage.clickOnElementAtHomePage("Click on 'Log in' button", homePage.getLoginButton());
        homePage.inputDataAtHomePage("Input invalid email", homePage.getEmailField(), "test2@gmail.com");
        homePage.inputDataAtHomePage("Input invalid password", homePage.getPasswordField(), "password");
        homePage.clickOnElementAtHomePage("Click on auth 'Log in' button", homePage.getLoginButtonAtAuth());
        asserts.assertTextPresentAtPage("Assert that login error text present", "Unpakt dashboard");
    }
}

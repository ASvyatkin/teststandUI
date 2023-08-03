package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait driverWait;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement usernameField;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//span[.='Login']")
    private WebElement loginButton;
    @FindBy(xpath = "//div[.='Copyright ⓒ 2022 . Geekbrains']")
    private WebElement copyright;
    @FindBy(xpath = "//h1[.='Blog']")
    private WebElement blogHeader;
    @FindBy(xpath = "//div[contains(@class,'error-block')]")
    private WebElement errorBlock;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public LoginPage(WebDriver driver, WebDriverWait driverWait) {
        this.driver = driver;
        this.driverWait = driverWait;
        PageFactory.initElements(driver, this);
    }
    public LoginPage textToUsernameField(String str) {
        usernameField.sendKeys(str);
        return this;
    }
    public LoginPage textToPasswordField(String str) {
        passwordField.sendKeys(str);
        return this;
    }
    public LoginPage loginButtonClick() {
        loginButton.click();
        return this;
    }
    public LoginPage waitUntilErrorIsVisible() {
        driverWait.until(ExpectedConditions.visibilityOf(errorBlock));
        return this;
    }
    public String getBlogText() {return blogHeader.getText();}
    public String getCopyrightText() {return copyright.getText();}
    public String getError() {return errorBlock.getText();}
    public LoginPage validLogin() {
        usernameField.sendKeys("AndreySvyatkin");
        passwordField.sendKeys("aa376a3bf0");
        loginButton.click();
        return this;
    }
}



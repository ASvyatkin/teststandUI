package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PagesHeader {

    private WebDriver driver;
    private WebDriverWait driverWait;

    @FindBy(xpath = "//a[contains(.,'Hello')]")
    private WebElement profileMenu;
    @FindBy(xpath = "//span[.='Logout']")
    private WebElement logoutButton;
    @FindBy(xpath = "//span[.='Home']")
    private WebElement homeButton;
    @FindBy(xpath = "//button[@role='switch']")
    private WebElement postsSwitch;
    @FindBy(xpath = "//div[.='Copyright ⓒ 2022 . Geekbrains']")
    private WebElement copyright;

    public PagesHeader(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PagesHeader(WebDriver driver, WebDriverWait driverWait) {
        this.driver = driver;
        this.driverWait = driverWait;
        PageFactory.initElements(driver, this);
    }

    public PagesHeader profileMenuClick() {
        profileMenu.click();
        return this;
    }

    public LoginPage logout(){
        profileMenu.click();
        driverWait.until(ExpectedConditions.visibilityOf(logoutButton));
        logoutButton.click();
        return new LoginPage(driver);
    }

    public MyPostsPage goToMyPostsPage() {
        homeButton.click();
        return new MyPostsPage(driver);
    }

    public NotMyPostsPage goToNOTMyPostsPage() {
        homeButton.click();
        postsSwitch.click();
        return new NotMyPostsPage(driver);
    }

    public String getCopyrightText() {return copyright.getText();}

}



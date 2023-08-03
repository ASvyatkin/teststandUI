package pages;

import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class MyPostsPage extends PagesHeader {

    private WebDriver driver;
    private WebDriverWait driverWait;

    @FindBy(xpath = "//div[@class='content']/div[contains(@class,'posts')]/a")
    private List<WebElement> posts;
    @FindBy(xpath = "//a[.='Next Page' and contains(@href,'page')]")
    private WebElement nextPageActive;
    @FindBy(xpath = "//a[.='Next Page' and contains(@class,'disabled')]")
    private WebElement nextPageDisabled;
    @FindBy(xpath = "//a[.='Previous Page' and contains(@href,'page')]")
    private WebElement previousPageActive;
    @FindBy(xpath = "//a[.='Previous Page' and contains(@class,'disabled')]")
    private WebElement previousPageDisabled;


    public MyPostsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MyPostsPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
        this.driver = driver;
        this.driverWait = driverWait;
        PageFactory.initElements(driver, this);
    }

    public boolean isNextPageDisabled() {
        return nextPageDisabled.isDisplayed();
    }

    public boolean isPreviousPageDisabled() {
        return previousPageDisabled.isDisplayed();
    }

    public boolean isNextPageActive() {
        return nextPageActive.isDisplayed();
    }

    public boolean isPreviousPageActive() {
        return previousPageActive.isDisplayed();
    }

    public MyPostsPage nextPageClick() {
        nextPageActive.click();
        return this;
    }

    public MyPostsPage previousPageClick() {
        previousPageActive.click();
        return this;
    }

    public List<WebElement> getPosts() {
        return posts;
    }
}



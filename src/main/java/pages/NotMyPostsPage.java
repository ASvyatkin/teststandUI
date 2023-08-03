package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotMyPostsPage extends PagesHeader {

    private WebDriver driver;
    private WebDriverWait driverWait;

    public NotMyPostsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public NotMyPostsPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}



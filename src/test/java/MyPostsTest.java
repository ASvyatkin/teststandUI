import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MyPostsPage;
import pages.PagesHeader;

import java.time.Duration;
import java.util.List;

public class MyPostsTest {

    Cookie auth;
    WebDriver firefox;
    WebDriverWait firefoxWait;
    String copyright = "Copyright ⓒ 2022 . Geekbrains";

    @BeforeAll
    static void setup() {WebDriverManager.firefoxdriver().setup();}
    @BeforeEach
    void driverCreate() {
        firefox = new FirefoxDriver();
        firefoxWait = new WebDriverWait(firefox, Duration.ofMillis(3000));
        auth = new Cookie("session_id", "%7B%22id%22%3A22209%2C%22username%22%3A%22AndreySvyatkin%22%2C%22token%22%3A%228568074306ad50730fbc9e0be0a16b09%22%2C%22roles%22%3A%5B%22R_DUMMY%22%2C%22R_USER%22%2C%22P_SUPPORT_MESSAGE_L%22%2C%22P_POST_L%22%2C%22P_POST_C%22%2C%22P_POST_R%22%2C%22P_POST_U%22%2C%22P_POST_D%22%2C%22P_USER_R%22%2C%22P_USER_U%22%2C%22P_USER_LOGOUT%22%2C%22P_USER_LOGIN%22%5D%7D");
        firefox.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

        firefox.get("https://test-stand.gb.ru");
        firefox.manage().addCookie(auth);
        firefox.navigate().refresh();
        new PagesHeader(firefox).goToMyPostsPage();
    }

    @Test
    @DisplayName("На странице 10 постов")
    void approvePostsCountOnPage() {
        List<WebElement> posts = new MyPostsPage(firefox).getPosts();
        Assertions.assertTrue(new MyPostsPage(firefox, firefoxWait).isNextPageActive());

        if(posts.size()==0) {Assertions.assertFalse(true);}
        Assertions.assertTrue(posts.size()==10);
    }

    @Test
    @DisplayName("Проверка пагинатора")
    void paginatorTest() {
        MyPostsPage page = new MyPostsPage(firefox);
        List<WebElement> posts = page.getPosts();

        if(posts.size()==4 && page.isPreviousPageDisabled() && page.isNextPageActive()) {
            page.nextPageClick();
            if(page.isPreviousPageActive()) {page.previousPageClick();} else {Assertions.assertFalse(true);}
        } else {Assertions.assertFalse(true);
            System.out.println("Возможно, количества постов недостаточно для теста!");}
    }

    @Test
    @DisplayName("Посты содержат в себе заголовок, картинку и описание")
    void postContents() {
        List<WebElement> posts = new MyPostsPage(firefox).getPosts();
        if(posts.size()==0) {Assertions.assertFalse(true);
            System.out.println("На странице нет постов!");}

        for(WebElement post: posts) {
            Assertions.assertTrue(post.findElement(By.tagName("h2")).isDisplayed());
            Assertions.assertTrue(post.findElement(By.className("description")).isDisplayed());
            Assertions.assertTrue(post.findElement(By.tagName("img")).isDisplayed());
        }
    }

    @Test
    @DisplayName("Наличие копирайта")
    void copyrightOnPage() {
        String actual = new MyPostsPage(firefox)
                .getCopyrightText();
        Assertions.assertEquals(copyright, actual);
    }

    @AfterEach
    void tearDown() {firefox.close();}
}

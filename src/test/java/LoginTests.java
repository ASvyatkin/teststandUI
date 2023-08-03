import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.PagesHeader;

import java.time.Duration;

public class LoginTests {
    WebDriver firefox;
    WebDriverWait firefoxWait;
    String copyright = "Copyright ⓒ 2022 . Geekbrains";

    @BeforeAll
    static void setup() {WebDriverManager.firefoxdriver().setup();}
    @BeforeEach
    void driverCreate() {
        firefox = new FirefoxDriver();
        firefoxWait = new WebDriverWait(firefox, Duration.ofMillis(3000));

        firefox.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        firefox.get("https://test-stand.gb.ru/login");

    }

    @Test
    @DisplayName("Авторизация с валидными данными")
    void validLogin() {
        String actual = new LoginPage(firefox)
                .validLogin()
                .getBlogText();

        Assertions.assertEquals("Blog", actual);
    }

    @Test
    @DisplayName("Ошибка при пустом логине или пароле")
    void missingPass() {
        String expected = "Поле не может быть пустым";
        String actualForPass = new LoginPage(firefox)
                .textToUsernameField("username")
                .loginButtonClick()
                .getError();

        firefox.navigate().refresh();

        String actualForUsername = new LoginPage(firefox)
                .textToPasswordField("666666")
                .loginButtonClick()
                .getError();

        Assertions.assertEquals(expected, actualForUsername);
        Assertions.assertEquals(expected, actualForPass);
    }

    @Test
    @DisplayName("Проверка левых граничных значений логина")
    void usernameBorders() {
        String expectedError = "Неправильный логин. Может быть не менее 3 и не более 20 символов";

        for (int i=0; i<=3; i++) {
            String username = "";
            for(int j=0; j<=i; j++) {username+="a";}

            if(i<3) {
                String actualError = new LoginPage(firefox, firefoxWait)
                        .textToUsernameField(username)
                        .textToPasswordField("666666")
                        .loginButtonClick()
                        .waitUntilErrorIsVisible()
                        .getError();

                Assertions.assertEquals(expectedError, actualError);
                firefox.navigate().refresh();
            } else if(i==3) {
                String actual = new LoginPage(firefox, firefoxWait)
                        .textToUsernameField(username)
                        .textToPasswordField("666666")
                        .loginButtonClick()
                        .waitUntilErrorIsVisible()
                        .getBlogText();

                Assertions.assertEquals("Blog", actual);
            }
        }
    }

    @Test
    @DisplayName("Наличие копирайта")
    void copyrightOnPage() {
        String actual = new LoginPage(firefox)
                .getCopyrightText();
        Assertions.assertEquals(copyright, actual);
    }

    @AfterEach
    void tearDown() {firefox.close();}
}

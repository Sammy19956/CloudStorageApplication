package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.Tests2.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.Tests2.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.Tests2.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests2 {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    private WebDriverWait wait;

    private String baseURL;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, 10);
        baseURL = "http://localhost:" + port;
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }


/* *********************
*/
    /**
     * Go to the homepage without logging in.
     */
    @Test
    public void tryToEnterUnauthorizedPage() {
        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    public void signupLoginAuthorization() {

        /**
         * Sign up, login and then check if the home page is accessible
         */
        signupAndLogin();
        Assertions.assertEquals("Home", driver.getTitle());

        /**
         * Logout and access the home page, it should not be accessible
         */
        driver.get(baseURL + "/home");
        HomePage homePage = new HomePage(driver, wait);
        homePage.logout();
        driver.get(baseURL + "/home");

        Assertions.assertEquals("Login", driver.getTitle());

    }

    @Test
    public void testNoteCreation() throws InterruptedException {
        signupAndLogin();
        HomePage homePage = new HomePage(driver, wait);
        homePage.createNote("Test Note", "The quick brown fox jumps over the lazy dog!");
        Assertions.assertEquals("Result", driver.getTitle());
        Assertions.assertEquals("Success", driver.findElement(By.id("success")).getText());

        driver.get(baseURL + "/home");

        homePage.ifNoteVisible();

        homePage.deleteNote();
    }

    @Test
    public void testNoteOperation() throws InterruptedException {
        signupAndLogin();
        HomePage homePage = new HomePage(driver, wait);
        homePage.createNote("Test Note", "The quick brown fox jumps over the lazy dog!");
        Assertions.assertEquals("Success", driver.findElement(By.id("success")).getText());

        driver.get(baseURL + "/home");

        homePage.editNote("s", "edited");
        Assertions.assertEquals("Result", driver.getTitle());
        Assertions.assertEquals("Success", driver.findElement(By.id("success")).getText());

        driver.get(baseURL + "/home");

        homePage.ifNoteEdited();

        homePage.deleteNote();
    }

    @Test
    public void testNoteDeletion() throws InterruptedException {
        signupAndLogin();
        HomePage homePage = new HomePage(driver, wait);
        homePage.createNote("Test Note", "The quick brown fox jumps over the lazy dog!");
        Assertions.assertEquals("Success", driver.findElement(By.id("success")).getText());

        driver.get(baseURL + "/home");

        homePage.deleteNote();
        Assertions.assertEquals("Result", driver.getTitle());
        Assertions.assertEquals("Success", driver.findElement(By.id("success")).getText());

        driver.get(baseURL + "/home");

        homePage.ifNoteDeleted();
    }

    @Test
    public void testCredentialCreationAndEncryption() throws InterruptedException {
        signupAndLogin();
        HomePage homePage = new HomePage(driver, wait);
        homePage.createCredential("www.instagram.com", "jdoe", "secret987");

        Assertions.assertEquals("Result", driver.getTitle());
        Assertions.assertEquals("Success", driver.findElement(By.id("success")).getText());

        driver.get(baseURL + "/home");

        homePage.ifCredentialVisible();

        homePage.deleteCredential();
    }

    @Test
    public void testCredentialOperation() throws InterruptedException {
        signupAndLogin();
        HomePage homePage = new HomePage(driver, wait);
        homePage.createCredential("www.instagram.com", "jdoe", "secret987");

        Assertions.assertEquals("Result", driver.getTitle());
        Assertions.assertEquals("Success", driver.findElement(By.id("success")).getText());

        driver.get(baseURL + "/home");

        homePage.editCredential("www.udacity.com", "jdoe", "newsecret987");

        driver.get(baseURL + "/home");

        homePage.ifCredentialEdited();

        homePage.deleteCredential();
    }

    @Test
    public void testCredentialDeletion() throws InterruptedException {
        signupAndLogin();
        HomePage homePage = new HomePage(driver, wait);
        homePage.createCredential("www.instagram.com", "jdoe", "secret987");
        Assertions.assertEquals("Success", driver.findElement(By.id("success")).getText());

        driver.get(baseURL + "/home");

        homePage.deleteCredential();
        Assertions.assertEquals("Result", driver.getTitle());
        Assertions.assertEquals("Success", driver.findElement(By.id("success")).getText());

        driver.get(baseURL + "/home");

        homePage.ifCredentialDeleted();
    }


/* *********************
*/
    public void signupAndLogin() {
        driver.get(baseURL + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("John", "Doe", "jdoe", "secret987");

        driver.get(baseURL + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("jdoe", "secret987");
    }

}
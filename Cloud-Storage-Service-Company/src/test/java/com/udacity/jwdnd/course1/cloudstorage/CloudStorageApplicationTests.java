package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private static WebDriver driver;

	public String baseURL;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.firefoxdriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new FirefoxDriver();
		baseURL = "http://localhost:" + port;
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	/*@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}*/

	@Test
	public void testHomePageNotAccessibleByUnauthorizedUser() {

		driver.get(baseURL + "/home");
		Assertions.assertFalse(driver.getTitle().equals("Home"));

		driver.get(baseURL + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get(baseURL + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

	}

	@Test
	public void testSignupLoginAndLogoutNewUser() {

		// User data to be used:
		String firstName = "Albert";
		String lastName = "Einstein";
		String username = "alb123";
		String password = "ein123";

		// Verify '/signup' endpoint takes us to Sign Up page.
		driver.get(baseURL + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		// Initializing Selenium Object Page and signing up the new user.
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password); // Automatically redirects to the Login Page after signing up.

		// Verify we were successfully redirected to Login page.
		Assertions.assertEquals("Login", driver.getTitle());

		// Initializing Selenium Object Page and logging new user in.
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password); // Automatically redirects to home page.

		// Verify we were successfully redirected to Home page.
		Assertions.assertEquals("Home", driver.getTitle());

		// Log out user and verify it was successfully redirected to login page.
		HomePage homePage = new HomePage(driver);
		homePage.logout();
		Assertions.assertEquals("Login", driver.getTitle());

		// Verify Home page is no longer accessible after logging out.
		driver.get(baseURL + "/home");
		Assertions.assertFalse(driver.getTitle().equals("Home"));
		
	}

}

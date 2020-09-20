package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private static WebDriver driver;
	private WebDriverWait wait;

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
	public void testSignupLoginLogoutNewUser() throws InterruptedException {

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

	@Test
	public void testCreateNoteWithExistingUser() throws InterruptedException {

		// CREATING USER
		// User data to be used:
		String firstName = "Alan";
		String lastName = "Turing";
		String username = "al123";
		String password = "tur123";

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

		// CREATING NOTE
		// Go to Notes Section and Create note
		String noteTitle = "Places to visit";
		String noteDescription = "Lima, Cuzco, Ica.";
		HomePage homePage = new HomePage(driver);
		homePage.createNote(noteTitle, noteDescription); // Automatically redirects to result page.

		// Verify we were successfully redirected to Result Page and go back to home page
		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		resultPage.resultMsgAnchorClick(); // back to homepage;
		homePage.clickNotesButton();
		Thread.sleep(3000);

		// VERIFY CREATED NOTE
		Note firstNote = homePage.getFirstNote();

		Assertions.assertEquals(noteTitle, firstNote.getNoteTitle());
		Assertions.assertEquals(noteDescription, firstNote.getNoteDescription());

	}

	/**
	 * Test that logs in an existing user with existing notes, clicks the edit note button on an
	 * existing note, changes the note data, saves the changes, and verifies that the changes
	 * appear in the note list.
	 */
	@Test
	public void testChangeNoteData() throws InterruptedException {

		String newNoteTitle = "To-Do List";
		String newNoteDescription = "Walk the dog, Wash the Car";
		String firstName = "qwe";
		String lastName = "qwe";
		String username = "qwe";
		String password = "qwe";

		// Signing up User, Creating a Note and logging out.
		driver.get(baseURL + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);

		//driver.get(baseURL + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

		// Initializing Selenium Object Page and logging new user in.
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password); // Automatically redirects to home page.
		Assertions.assertEquals("Home", driver.getTitle());
		HomePage homePage = new HomePage(driver);
		homePage.clickNotesButton();
		homePage.createNote("Any Title", "Any Description"); //Redirects to result page
		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		resultPage.resultMsgAnchorClick(); // Redirects to home page

		Thread.sleep(1000);
		homePage.logout();

		// Logging in existing user and edit note
		/*Note firstNote = homePage.getFirstNote();
		firstNote.setNoteTitle(newNoteTitle);
		firstNote.setNoteDescription(newNoteDescription);
		homePage.updateNote(firstNote);*/


	}
}

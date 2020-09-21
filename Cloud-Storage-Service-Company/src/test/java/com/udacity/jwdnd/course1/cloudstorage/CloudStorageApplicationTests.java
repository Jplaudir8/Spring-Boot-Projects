package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
	private JavascriptExecutor js;

	public String baseURL;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.firefoxdriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new FirefoxDriver();
		baseURL = "http://localhost:" + port;
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 5);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	/**
	 * Test that verifies that the home page is not accessible
	 * without logging in.
	 */
	@Test
	public void testHomePageNotAccessibleByUnauthorizedUser() {

		driver.get(baseURL + "/home");
		Assertions.assertFalse(driver.getTitle().equals("Home"));

		driver.get(baseURL + "/login");
		Assertions.assertEquals("Login", driver.getTitle());

		driver.get(baseURL + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

	}

	/**
	 * Test that signs up a new user, logs that user in, verifies that they
	 * can access the home page, then logs out and verifies that the home
	 * page is no longer accessible.
	 */
	@Test
	public void testSignupLoginLogoutNewUser() {

		// New User data to be used:
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
		homePage.clickLogoutButton();
		Assertions.assertEquals("Login", driver.getTitle());

		// Verify Home page is no longer accessible after logging out.
		driver.get(baseURL + "/home");
		Assertions.assertFalse(driver.getTitle().equals("Home"));

	}

	/**
	 * Test that logs in an existing user, creates a note and verifies
	 * that the note details are visible in the note list.
	 * @throws InterruptedException
	 */
	@Test
	public void testCreateNoteWithExistingUser() {

		// CREATING USER
		// User data to be used:
		String firstName = "Alan";
		String lastName = "Turing";
		String username = "al123";
		String password = "tur123";

		// Verify '/signup' endpoint takes us to Sign Up page.
		driver.get(baseURL + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());

		// Initializing Selenium Page Object and signing up the new user.
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password); // Automatically redirects to the Login Page after signing up.

		// Verify we were successfully redirected to Login page.
		Assertions.assertEquals("Login", driver.getTitle());

		// Initializing Selenium Page Object and logging new user in.
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password); // Automatically redirects to home page.

		// Verify we were successfully redirected to Home page.
		Assertions.assertEquals("Home", driver.getTitle());

		// CREATING NOTE
		// Go to Notes Section and Create note
		String noteTitle = "Places to visit";
		String noteDescription = "Lima, Cuzco, Ica.";

		HomePage homePage = new HomePage(driver);
		homePage.clickNotesTabButton();
		homePage.createNote(noteTitle, noteDescription); // Automatically redirects to result page.

		// Verify we were successfully redirected to Result Page and go back to home page
		wait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		resultPage.resultMsgAnchorClick(); // back to homepage;
		wait.until(ExpectedConditions.titleContains("Home"));
		homePage.clickNotesTabButton();

		// VERIFY CREATED NOTE
		homePage.clickNotesEditButton();
		homePage.waitNoteModelPage();
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
	public void testChangeNoteData() {

		// Data to be used
		String firstName = "qwe";
		String lastName = "qwe";
		String username = "qwe";
		String password = "qwe";

		// Signing up User, Creating a Note and logging out.
		driver.get(baseURL + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);
		wait.until(ExpectedConditions.titleContains("Login"));
		Assertions.assertEquals("Login", driver.getTitle());
		// Initializing Selenium Object Page and logging new user in.
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password); // Automatically redirects to home page.
		wait.until(ExpectedConditions.titleContains("Home"));
		Assertions.assertEquals("Home", driver.getTitle());

		// Creating Note
		HomePage homePage = new HomePage(driver);
		homePage.clickNotesTabButton();
		homePage.createNote("Any Title", "Any Description"); //Redirects to result page
		wait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		resultPage.resultMsgAnchorClick(); // Redirects to home page
		wait.until(ExpectedConditions.titleContains("Home"));
		Assertions.assertEquals("Home", driver.getTitle());
		homePage.clickLogoutButton();
		wait.until(ExpectedConditions.titleContains("Login"));
		Assertions.assertEquals("Login", driver.getTitle());

		// LOGGING IN EXISTING USER AND EDITING NOTE.
		String newNoteTitle = "To-Do List";
		String newNoteDescription = "Walk the dog, Wash the Car";
		loginPage.login("qwe", "qwe");
		wait.until(ExpectedConditions.titleContains("Home"));
		Assertions.assertEquals("Home", driver.getTitle());

		homePage.clickNotesTabButton();
		homePage.clickNotesEditButton();
		homePage.waitNoteModelPage();
		Note firstNote = homePage.getFirstNote();
		homePage.updateNote(firstNote, newNoteTitle, newNoteDescription); //Redirects to result page
		wait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Result", driver.getTitle());
		resultPage.resultMsgAnchorClick(); // Redirects to home page


		// VERIFY EDITED NOTE
		homePage.clickNotesTabButton();
		homePage.clickNotesEditButton();
		homePage.waitNoteModelPage();
		Note newNote = homePage.getFirstNote();

		Assertions.assertEquals(newNoteTitle, newNote.getNoteTitle());
		Assertions.assertEquals(newNoteDescription, newNote.getNoteDescription());
	}
}

package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
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

	public String baseURL;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.firefoxdriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new FirefoxDriver();
		baseURL = "http://localhost:" + port;
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
	@Order(1)
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
	@Order(2)
	@Test
	public void testSignupLoginLogoutNewUser() {

		// New User data to be used:
		String firstName = "Albert";
		String lastName = "Einstein";
		String username = "alb123";
		String password = "ein123";

		// Verify '/signup' endpoint takes us to Sign Up page.
		driver.get(baseURL + "/signup");
		wait.until(ExpectedConditions.titleContains("Sign Up"));
		Assertions.assertEquals("Sign Up", driver.getTitle());

		// Initializing Selenium Object Page and signing up the new user.
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password); // Automatically redirects to the Login Page after signing up.

		// Verify we were successfully redirected to Login page.
		wait.until(ExpectedConditions.titleContains("Login"));
		Assertions.assertEquals("Login", driver.getTitle());

		// Initializing Selenium Object Page and logging new user in.
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username, password); // Automatically redirects to home page.

		// Verify we were successfully redirected to Home page.
		wait.until(ExpectedConditions.titleContains("Home"));
		Assertions.assertEquals("Home", driver.getTitle());

		// Log out user and verify it was successfully redirected to login page.
		HomePage homePage = new HomePage(driver);
		homePage.clickLogoutButton();
		wait.until(ExpectedConditions.titleContains("Login"));
		Assertions.assertEquals("Login", driver.getTitle());

		// Verify Home page is no longer accessible after logging out.
		driver.get(baseURL + "/home");
		Assertions.assertFalse(driver.getTitle().equals("Home"));

	}

	/**
	 * Test that logs in an existing user, creates a note and verifies
	 * that the note details are visible in the note list.
	 */
	@Order(3)
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

		// VERIFY NOTE WAS CREATED
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
	@Order(4)
	@Test
	public void testEditNote() {

		// Data to be used
		String firstName = "Larry";
		String lastName = "Page";
		String username = "larr123";
		String password = "page123";

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
		loginPage.login(username, password);
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

	/**
	 * Test that logs in an existing user with existing notes, clicks the delete
	 * note button on an existing note, and verifies that the note no longer
	 * appears in the note list.
	 */
	@Order(5)
	@Test
	public void testDeleteNote() {
		// Data to be used
		String firstName = "Jeff";
		String lastName = "Bezos";
		String username = "jeff123";
		String password = "bez123";

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

		// LOGGING IN EXISTING USER AND DELETING NOTE.
		loginPage.login(username, password);
		wait.until(ExpectedConditions.titleContains("Home"));
		Assertions.assertEquals("Home", driver.getTitle());
		homePage.clickNotesTabButton();
		homePage.clickNotesDeleteAnchor();
		wait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Result", driver.getTitle());
		resultPage.resultMsgAnchorClick(); // Redirects to home page
		wait.until(ExpectedConditions.titleContains("Home"));
		Assertions.assertEquals("Home", driver.getTitle());

		// VERIFYING NOTE WAS SUCCESSFULLY DELETED
		homePage.clickNotesTabButton();
		Assertions.assertFalse(homePage.firstNoteExists());

	}

	/**
	 * Test that logs in an existing user, creates a credential and
	 * verifies that the credential details are visible in the
	 * credential list.
	 */
	@Order(6)
	@Test
	public void testCreateCredential() {
		// Data to be used
		String firstName = "Elon";
		String lastName = "Musk";
		String username = "elo123";
		String password = "mus123";

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

		// CREATING CREDENTIALS
		String credentialUrl = "www.google.com";
		String credentialUsername = "Martinez";
		String credentialPassword = "Brothers";
		HomePage homePage = new HomePage(driver);
		homePage.clickCredentialsTabButton();
		homePage.createCredentials(credentialUrl, credentialUsername, credentialPassword);
		wait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		resultPage.resultMsgAnchorClick(); // Redirects to home page
		wait.until(ExpectedConditions.titleContains("Home"));
		Assertions.assertEquals("Home", driver.getTitle());
		homePage.clickCredentialsTabButton();


		// VERIFY CREDENTIALS WERE CREATED
		homePage.clickCredentialsEditButton();
		homePage.waitCredentialsModelPage();
		Credential firstCredentials = homePage.getFirstCredentials();

		Assertions.assertEquals(credentialUrl, firstCredentials.getUrl());
		Assertions.assertEquals(credentialUsername, firstCredentials.getUsername());
		Assertions.assertEquals(credentialPassword, firstCredentials.getPassword());

	}

	/**
	 * Test that logs in an existing user with existing credentials, clicks
	 * the edit credential button on an existing credential, changes the
	 * credential data, saves the changes, and verifies that the changes
	 * appear in the credential list.
	 */
	@Order(7)
	@Test
	public void testEditCredential() {
		// Data to be used
		String firstName = "Neil";
		String lastName = "Armstrong";
		String username = "neil123";
		String password = "arm123";

		// Signing up User, Creating a Credential and logging out.
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
		// CREATING CREDENTIALS
		String credentialUrl = "www.google.com";
		String credentialUsername = "Martinez";
		String credentialPassword = "Brothers";
		HomePage homePage = new HomePage(driver);
		homePage.clickCredentialsTabButton();
		homePage.createCredentials(credentialUrl, credentialUsername, credentialPassword);
		wait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		resultPage.resultMsgAnchorClick(); // Redirects to home page
		wait.until(ExpectedConditions.titleContains("Home"));
		Assertions.assertEquals("Home", driver.getTitle());
		homePage.clickLogoutButton();
		wait.until(ExpectedConditions.titleContains("Login"));
		Assertions.assertEquals("Login", driver.getTitle());

		// LOGGING IN EXISTING USER AND EDITING CREDENTIALS.
		String newUrlCredential = "www.facebook.com";
		String newUsernameCredential = "Buzz";
		String newPasswordCredential = "Aldrin";
		loginPage.login(username, password); // Automatically redirects to home page.
		wait.until(ExpectedConditions.titleContains("Home"));
		Assertions.assertEquals("Home", driver.getTitle());
		homePage.clickCredentialsTabButton();

		// EDITING CREDENTIALS
		homePage.clickCredentialsEditButton();
		homePage.waitCredentialsModelPage();
		Credential firstCredentials = homePage.getFirstCredentials();

		homePage.updateCredentials(firstCredentials, newUrlCredential, newUsernameCredential, newPasswordCredential); //Redirects to result page
		wait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Result", driver.getTitle());
		resultPage.resultMsgAnchorClick(); // Redirects to Home Page
		wait.until(ExpectedConditions.titleContains("Home"));
		Assertions.assertEquals("Home", driver.getTitle());

		// VERIFY EDITED CREDENTIALS
		homePage.clickCredentialsTabButton();
		homePage.clickCredentialsEditButton();
		homePage.waitCredentialsModelPage();
		Credential newCredentials = homePage.getFirstCredentials();
		Assertions.assertEquals(newUrlCredential, newCredentials.getUrl());
		Assertions.assertEquals(newUsernameCredential, newCredentials.getUsername());
		Assertions.assertEquals(newPasswordCredential, newCredentials.getPassword());
	}

	/**
	 * Test that logs in an existing user with existing credentials,
	 * clicks the delete credential button on an existing credential,
	 * and verifies that the credential no longer appears in the
	 * credential list.
	 */
	@Order(8)
	@Test
	public void testDeleteCredential() {
		// Data to be used
		String firstName = "Michael";
		String lastName = "Jackson";
		String username = "mich123";
		String password = "jack123";

		// Signing up User, Creating a Credential and logging out.
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
		// CREATING CREDENTIALS
		String credentialUrl = "www.google.com";
		String credentialUsername = "Martinez";
		String credentialPassword = "Brothers";
		HomePage homePage = new HomePage(driver);
		homePage.clickCredentialsTabButton();
		homePage.createCredentials(credentialUrl, credentialUsername, credentialPassword);
		wait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		resultPage.resultMsgAnchorClick(); // Redirects to home page
		wait.until(ExpectedConditions.titleContains("Home"));
		Assertions.assertEquals("Home", driver.getTitle());
		homePage.clickLogoutButton();
		wait.until(ExpectedConditions.titleContains("Login"));
		Assertions.assertEquals("Login", driver.getTitle());

		// LOGGING IN EXISTING USER AND DELETING CREDENTIALS.
		String newUrlCredential = "www.facebook.com";
		String newUsernameCredential = "Buzz";
		String newPasswordCredential = "Aldrin";
		loginPage.login(username, password); // Automatically redirects to home page.
		wait.until(ExpectedConditions.titleContains("Home"));
		Assertions.assertEquals("Home", driver.getTitle());
		homePage.clickCredentialsTabButton();

		// DELETING CREDENTIALS
		homePage.clickCredentialDeleteAnchor();
		wait.until(ExpectedConditions.titleContains("Result"));
		Assertions.assertEquals("Result", driver.getTitle());
		resultPage.resultMsgAnchorClick(); // Redirects to home page
		wait.until(ExpectedConditions.titleContains("Home"));
		Assertions.assertEquals("Home", driver.getTitle());

		// VERIFYING NOTE WAS SUCCESSFULLY DELETED
		homePage.clickCredentialsTabButton();
		Assertions.assertFalse(homePage.firstCredentialExists());

	}
}

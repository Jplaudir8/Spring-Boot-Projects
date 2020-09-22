package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {


    @FindBy(css="#logout-submit-button")
    private WebElement logoutSubmitButton;

    // Note WebElements
    @FindBy(css="#nav-notes-tab")
    private WebElement notesTabButton;

    @FindBy(css="#addNoteButton")
    private WebElement addNoteButton;

    @FindBy(css="#note-title")
    private WebElement noteTitleInput;

    @FindBy(css="#note-description")
    private WebElement noteDescriptionInput;

    @FindBy(css="#noteSaveChangesButton")
    private WebElement noteSaveChangesButton;

    @FindBy(css="#noteEditButton")
    private WebElement noteEditButton;

    @FindBy(css="#noteDeleteButton")
    private WebElement noteDeleteButton;

    @FindBy(css="#noteModalLabel")
    private WebElement noteModalLabel;

    // Credentials WebElements
    @FindBy(css="#nav-credentials-tab")
    private WebElement credentialsTabButton;

    @FindBy(css="#addCredentialsButton")
    private WebElement addCredentialsButton;

    @FindBy(css="#credentialModalLabel")
    private WebElement credentialModalLabel;

    @FindBy(css="#credential-url")
    private WebElement credentialUrlInput;

    @FindBy(css="#credential-username")
    private WebElement credentialUsernameInput;

    @FindBy(css="#credential-password")
    private WebElement credentialPasswordInput;

    @FindBy(css="#credentialSaveChangesButton")
    private WebElement credentialSaveChangesButton;

    @FindBy(css="#credentialEditButton")
    private WebElement credentialEditButton;

    private WebDriver driver;
    private WebDriverWait wait;
    private final JavascriptExecutor js;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.driver = webDriver;
        this.wait = new WebDriverWait(webDriver, 5);
        this.js = (JavascriptExecutor) webDriver;
    }

    /**
     * Log out user
     */
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutSubmitButton));
        js.executeScript("arguments[0].click();", logoutSubmitButton);
    }

    // NOTES TAB

    /**
     * Click 'Notes' tab
     */
    public void clickNotesTabButton() {
        wait.until(ExpectedConditions.elementToBeClickable(notesTabButton));
        js.executeScript("arguments[0].click();", notesTabButton);
    }

    /**
     * Click 'Edit' Button
     */
    public void clickNotesEditButton() {
        wait.until(ExpectedConditions.elementToBeClickable(noteEditButton));
        js.executeScript("arguments[0].click();", noteEditButton);
    }

    /**
     * Click 'Delete' Button
     */
    public void clickNotesDeleteAnchor() {
        wait.until(ExpectedConditions.elementToBeClickable(noteDeleteButton)).click();
        //js.executeScript("arguments[0].click;", noteDeleteButton);
    }

    /**
     * Retrieve true or false if note exists or no respectively.
     *
     * @return
     */
    public boolean firstNoteExists() {
        return elementExists("noteTitleRow") && elementExists("noteDescriptionRow");
    }

    /**
     * Verify if element exists
     */
    public boolean elementExists(String elementId){
        try {
            driver.findElement(By.id(elementId));
            return true;
        } catch(NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Wait for 'Notes' Modal to load
     */
    public void waitNoteModelPage() {
        wait.until(ExpectedConditions.elementToBeClickable(noteModalLabel));
    }

    // Helper Method
    private String getValueFromInput(String inputId) {
        WebElement input = driver.findElement(By.id(inputId));
        wait.until(ExpectedConditions.elementToBeClickable(input)).click();
        js.executeScript("arguments[0].click();", input);
        return input.getAttribute("value");
    }

    /**
     * Create a new 'Note'
     *
     * @param noteTitle Note Title for the new note.
     * @param noteDescription Note Description for the new note.
     */
    public void createNote(String noteTitle, String noteDescription) {
        wait.until(ExpectedConditions.elementToBeClickable(addNoteButton));
        js.executeScript("arguments[0].click();", addNoteButton);
        waitNoteModelPage();
        js.executeScript("arguments[0].value='"+ noteTitle +"';", this.noteTitleInput);
        js.executeScript("arguments[0].value='"+ noteDescription +"';", this.noteDescriptionInput);
        js.executeScript("arguments[0].click();", noteSaveChangesButton);
    }

    /**
     * Get 1st note created.
     *
     * @return Note object and its attributes set with the values retrieved from inputs.
     */
    public Note getFirstNote() {
        // Retrieve data and insert it in firstNote object
        String firstNoteTitle = getValueFromInput("note-title");
        String firstNoteDescription = getValueFromInput("note-description");
        //System.out.print("Title: " + noteTitleInput.getText() + " and Description: " + noteDescriptionInput.getText());
        Note firstNote = new Note(null, firstNoteTitle, firstNoteDescription,null);
        return firstNote;
    }

    /**
     * Update current note
     *
     * @param firstNote Note retrieved from modal
     * @param newNoteTitle new note title to replace current into note title
     * @param newNoteDescription new note description to replace into current note description
     */
    public void updateNote(Note firstNote, String newNoteTitle, String newNoteDescription) {
        firstNote.setNoteTitle(newNoteTitle);
        firstNote.setNoteDescription(newNoteDescription);
        // Open Edit View
        js.executeScript("arguments[0].click();", noteEditButton);
        // Clear current data
        noteTitleInput.clear();
        noteDescriptionInput.clear();
        // Insert new data of note object into input fields
        js.executeScript("arguments[0].value='" + firstNote.getNoteTitle() + "';", this.noteTitleInput);
        js.executeScript("arguments[0].value='" + firstNote.getNoteDescription() + "';", this.noteDescriptionInput);
        js.executeScript("arguments[0].click();", noteSaveChangesButton);
    }


    // CREDENTIALS TAB

    /**
     * Wait for 'Credentials' Modal to load
     */
    public void waitCredentialsModelPage() {
        wait.until(ExpectedConditions.elementToBeClickable(credentialModalLabel));
    }

    /**
     * Click 'Credentials' tab
     */
    public void clickCredentialsTabButton() {
        wait.until(ExpectedConditions.elementToBeClickable(credentialsTabButton));
        js.executeScript("arguments[0].click();", credentialsTabButton);
    }

    /**
     * Create new credentials.
     *
     * @param credentialUrl
     * @param credentialUsername
     * @param credentialPassword
     */
    public void createCredentials(String credentialUrl, String credentialUsername, String credentialPassword) {
        wait.until(ExpectedConditions.elementToBeClickable(addCredentialsButton));
        js.executeScript("arguments[0].click();", addCredentialsButton);
        waitCredentialsModelPage();
        js.executeScript("arguments[0].value='"+ credentialUrl +"';", this.credentialUrlInput);
        js.executeScript("arguments[0].value='"+ credentialUsername +"';", this.credentialUsernameInput);
        js.executeScript("arguments[0].value='"+ credentialPassword +"';", this.credentialPasswordInput);
        js.executeScript("arguments[0].click();", credentialSaveChangesButton);
    }

    /**
     * Click Credentials 'Edit' Button
     */
    public void clickCredentialsEditButton() {
        wait.until(ExpectedConditions.elementToBeClickable(credentialEditButton));
        js.executeScript("arguments[0].click();", credentialEditButton);
    }

    /**
     * Get 1st Credentials created
     *
     * @return
     */
    public Credential getFirstCredentials() {
        // Retrieve data and insert it in firstNote object
        String url = getValueFromInput("credential-url");
        String username = getValueFromInput("credential-username");
        String password = getValueFromInput("credential-password");
        Credential firstCredential = new Credential(null, url, username, null, password, null);
        return firstCredential;
    }

    public void updateCredentials(Credential firstCredentials, String newUrlCredential, String newUsernameCredential, String newPasswordCredential) {
        firstCredentials.setUrl(newUrlCredential);
        firstCredentials.setUsername(newUsernameCredential);
        firstCredentials.setPassword(newPasswordCredential);
        // Open Edit View
        js.executeScript("arguments[0].click();", credentialEditButton);
        // Clear current data
        credentialUrlInput.clear();
        credentialUsernameInput.clear();
        credentialPasswordInput.clear();
        // Insert new data of note object into input fields
        js.executeScript("arguments[0].value='" + firstCredentials.getUrl() + "';", this.credentialUrlInput);
        js.executeScript("arguments[0].value='" + firstCredentials.getUsername() + "';", this.credentialUsernameInput);
        js.executeScript("arguments[0].value='" + firstCredentials.getPassword() + "';", this.credentialPasswordInput);
        js.executeScript("arguments[0].click();", credentialSaveChangesButton);
    }

}

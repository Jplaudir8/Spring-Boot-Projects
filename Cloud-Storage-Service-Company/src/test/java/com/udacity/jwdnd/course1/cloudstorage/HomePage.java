package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    @FindBy(css="#logout-submit-button")
    private WebElement logoutSubmitButton;

    @FindBy(css="#nav-notes-tab")
    private WebElement notesTabButton;

    @FindBy(css="#addNoteButton")
    private WebElement addNoteButton;

    @FindBy(css="#note-title")
    private WebElement noteTitleInput;

    @FindBy(css="#note-description")
    private WebElement noteDescriptionInput;

    @FindBy(css="#saveChangesButton")
    private WebElement saveChangesButton;

    @FindBy(css="#noteEditButton")
    private WebElement noteEditButton;

    @FindBy(css="#noteModalLabel")
    private WebElement noteModalLabel;

    private WebDriver driver;
    private WebDriverWait wait;
    private final JavascriptExecutor js;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.driver = webDriver;
        this.wait = new WebDriverWait(webDriver, 5);
        this.js = (JavascriptExecutor) webDriver;
    }

    // Log out user
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
    public void clickNotesEditButton(){
        wait.until(ExpectedConditions.elementToBeClickable(noteEditButton));
        js.executeScript("arguments[0].click();", noteEditButton);
    }

    /**
     * Wait for 'Notes' Modal to load
     */
    public void waitNoteModelPage(){
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
        js.executeScript("arguments[0].click();", saveChangesButton);
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
        Note newNote = firstNote;
        newNote.setNoteTitle(newNoteTitle);
        newNote.setNoteDescription(newNoteDescription);
        // Open Edit View
        js.executeScript("arguments[0].click();", noteEditButton);
        // Clear current data
        noteTitleInput.clear();
        noteDescriptionInput.clear();
        // Insert new data of note object into input fields
        js.executeScript("arguments[0].value='" + newNote.getNoteTitle() + "';", this.noteTitleInput);
        js.executeScript("arguments[0].value='" + newNote.getNoteDescription() + "';", this.noteDescriptionInput);
        js.executeScript("arguments[0].click();", saveChangesButton);

    }


}

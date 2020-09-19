package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(css="#logout-submit-button")
    private WebElement logoutSubmitButton;

    @FindBy(css="#nav-notes-tab")
    private WebElement notesTabButton;

    @FindBy(css="#addNoteButton")
    private WebElement addNoteButton;

    @FindBy(xpath="//input[@id='note-title']")
    private WebElement noteTitleInput;

    @FindBy(xpath="//textarea[@id='note-description']")
    private WebElement noteDescriptionInput;

    @FindBy(css="#saveChangesButton")
    private WebElement saveChangesButton;

    @FindBy(css="#noteEditButton")
    private WebElement noteEditButton;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void logout() {
        this.logoutSubmitButton.click();
    }


    // NOTES TAB

    public void clickNotesButton() throws InterruptedException {
        this.notesTabButton.click();
        Thread.sleep(2000);
    }

    public void createNote(String noteTitle, String noteDescription) throws InterruptedException {
        clickNotesButton();
        this.addNoteButton.click();
        this.noteTitleInput.sendKeys(noteTitle);
        this.noteDescriptionInput.sendKeys(noteDescription);
        System.out.print("Title: " + noteTitleInput.getText() + " and Description: " + noteDescriptionInput.getText());
        Thread.sleep(3000);
        this.saveChangesButton.click();
        Thread.sleep(4000);
    }

    public Note getFirstNote() throws InterruptedException {
        // Open Edit View
        noteEditButton.click();
        Thread.sleep(4000);
        // Retrieve data and insert it in firstNote object
        String firstNoteTitle = noteTitleInput.getText();
        String firstNoteDescription = noteDescriptionInput.getText();
        //System.out.print("Title: " + noteTitleInput.getText() + " and Description: " + noteDescriptionInput.getText());
        Note firstNote = new Note(null, firstNoteTitle, firstNoteDescription,null);
        return firstNote;
    }

    public void updateNote(Note note) throws InterruptedException {

        Note newNote = note;

        // Open Edit View
        noteEditButton.click();
        Thread.sleep(2000);

        // Clear current data
        noteTitleInput.clear();
        noteDescriptionInput.clear();
        Thread.sleep(2000);

        // Insert new data
        noteTitleInput.sendKeys(note.getNoteTitle());
        noteDescriptionInput.sendKeys(note.getNoteDescription());
        Thread.sleep(2000);

        saveChangesButton.click();

    }


}

package com.udacity.jwdnd.course1.cloudstorage.Tests2;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {

    @FindBy(id = "btnLogout")
    private WebElement logoutButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;
    // These are availalable when the note tab is clicked.
    private WebElement addNoteButton;
    private WebElement noteTitle;
    private WebElement noteDescription;

    @FindBy(id = "note-submit")
    private WebElement submitNoteButton;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialTab;
    private WebElement addCredentialButton;
    private WebElement credentialURL;
    private WebElement credentialUsername;
    private WebElement credentialPassword;

    @FindBy(id = "btnCredentialSaveChanges")
    private WebElement submitCredentialButton;

    private WebDriverWait wait;
    private WebDriver webDriver;


    public HomePage(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
        PageFactory.initElements(webDriver, this);
    }

    public void logout() {
        logoutButton.click();
    }

    public void createNote(String noteTitle, String noteDescription) throws InterruptedException {
        Thread.sleep(1000);
        this.noteTab.click();

        this.wait.until(ExpectedConditions.elementToBeClickable(By.id("btnAddNewNote")));
        addNoteButton = webDriver.findElement(By.id("btnAddNewNote"));
        this.addNoteButton.click();

        this.noteTitle = webDriver.findElement(By.id("note-title"));
        this.wait.until(ExpectedConditions.visibilityOf(this.noteTitle));
        this.noteTitle.sendKeys(noteTitle);

        this.noteDescription = webDriver.findElement(By.id("note-description"));
        this.noteDescription.sendKeys(noteDescription);
        this.submitNoteButton.click();
    }

    public void ifNoteDeleted() throws InterruptedException {
        Thread.sleep(1000);
        this.noteTab.click();

        List rows = webDriver.findElements(By.xpath("//*[@id='userTable']/tbody/tr"));
        Assertions.assertEquals(0, rows.size());
    }

    public void ifNoteEdited() throws InterruptedException {
        Thread.sleep(1000);
        this.noteTab.click();
        //Thread.sleep(1000);
        this.wait.until(ExpectedConditions.visibilityOf
                (this.webDriver.findElement(By.xpath("//*[@id='userTable']/tbody/tr/th"))));
        noteTitle = this.webDriver.findElement(By.xpath("//*[@id='userTable']/tbody/tr/th"));
        noteDescription = this.webDriver.findElement(By.xpath("//*[@id='userTable']/tbody/tr/td[2]"));

        Assertions.assertEquals("Test Notes", noteTitle.getText());
        Assertions.assertEquals("The quick brown fox jumps over the lazy dog!edited", noteDescription.getText());
    }

    public void ifNoteVisible() throws InterruptedException {
        Thread.sleep(1000);
        this.noteTab.click();

        this.wait.until(ExpectedConditions.visibilityOf
                (this.webDriver.findElement(By.xpath("//*[@id='userTable']/tbody/tr/th"))));
        noteTitle = this.webDriver.findElement(By.xpath("//*[@id='userTable']/tbody/tr/th"));
        noteDescription = this.webDriver.findElement(By.xpath("//*[@id='userTable']/tbody/tr/td[2]"));

        Assertions.assertEquals("Test Note", noteTitle.getText());
        Assertions.assertEquals("The quick brown fox jumps over the lazy dog!", noteDescription.getText());

    }

    public void editNote(String noteTitle, String noteDescription) throws InterruptedException {
        Thread.sleep(1000);
        this.noteTab.click();

        WebElement editButton = this.webDriver.findElement(By.xpath("//*[@id='userTable']/tbody/tr/td[1]/button"));
        this.wait.until(ExpectedConditions.elementToBeClickable(editButton));
        editButton.click();

        this.noteTitle = webDriver.findElement(By.id("note-title"));
        this.wait.until(ExpectedConditions.visibilityOf(this.noteTitle));
        this.noteTitle.sendKeys(noteTitle);

        this.noteDescription = webDriver.findElement(By.id("note-description"));
        this.noteDescription.sendKeys(noteDescription);
        this.submitNoteButton.click();
    }

    public void deleteNote() throws InterruptedException {
        Thread.sleep(1000);
        this.noteTab.click();
        WebElement deleteButton = this.webDriver.findElement(By.xpath("//*[@id='userTable']/tbody/tr/td[1]/a"));
        this.wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButton.click();
    }

    public void createCredential(String url, String username, String password) throws InterruptedException {
        Thread.sleep(1000);
        this.credentialTab.click();

        this.wait.until(ExpectedConditions.elementToBeClickable(By.id("btnAddNewCredential")));
        addCredentialButton = webDriver.findElement(By.id("btnAddNewCredential"));
        this.addCredentialButton.click();

        this.credentialURL = webDriver.findElement(By.id("credential-url"));
        this.wait.until(ExpectedConditions.visibilityOf(this.credentialURL));
        this.credentialURL.sendKeys(url);

        this.credentialUsername = webDriver.findElement(By.id("credential-username"));
        this.credentialUsername.sendKeys(username);

        this.credentialPassword = webDriver.findElement(By.id("credential-password"));
        this.credentialPassword.sendKeys(password);

        this.submitCredentialButton.click();

    }

    public void ifCredentialVisible() throws InterruptedException {
        Thread.sleep(1000);
        this.credentialTab.click();

        this.wait.until(ExpectedConditions.visibilityOf
                (this.webDriver.findElement(By.xpath("//*[@id='credentialTable']/tbody/tr/th"))));
        credentialURL = this.webDriver.findElement(By.xpath("//*[@id='credentialTable']/tbody/tr/th"));
        credentialUsername = this.webDriver.findElement(By.xpath("//*[@id='credentialTable']/tbody/tr/td[2]"));
        credentialPassword = this.webDriver.findElement(By.xpath("//*[@id='credentialTable']/tbody/tr/td[3]"));

        Assertions.assertEquals("jdoe", credentialUsername.getText());
        Assertions.assertEquals("www.instagram.com", credentialURL.getText());
        Assertions.assertNotEquals("secret987", credentialPassword.getText());


    }

    public void editCredential(String url, String username, String password) throws InterruptedException {
        Thread.sleep(1000);
        this.credentialTab.click();
        WebElement editButton = this.webDriver.findElement(By.xpath("//*[@id='credentialTable']/tbody/tr/td[1]/button"));
        this.wait.until(ExpectedConditions.elementToBeClickable(editButton));
        editButton.click();

        credentialURL = webDriver.findElement(By.id("credential-url"));
        this.wait.until(ExpectedConditions.visibilityOf(this.credentialURL));
        credentialURL.clear();
        credentialURL.sendKeys(url);

        credentialUsername = webDriver.findElement(By.id("credential-username"));
        credentialUsername.clear();
        credentialUsername.sendKeys(username);

        credentialPassword = webDriver.findElement(By.id("credential-password"));

        Assertions.assertEquals("secret987", credentialPassword.getAttribute("value"));

        credentialPassword.clear();
        credentialPassword.sendKeys(password);

        this.submitCredentialButton.click();
    }

    public void ifCredentialEdited() throws InterruptedException {
        Thread.sleep(1000);
        this.credentialTab.click();

        this.wait.until(ExpectedConditions.visibilityOf
                (this.webDriver.findElement(By.xpath("//*[@id='credentialTable']/tbody/tr/th"))));
        credentialURL = this.webDriver.findElement(By.xpath("//*[@id='credentialTable']/tbody/tr/th"));
        credentialUsername = this.webDriver.findElement(By.xpath("//*[@id='credentialTable']/tbody/tr/td[2]"));
        credentialPassword = this.webDriver.findElement(By.xpath("//*[@id='credentialTable']/tbody/tr/td[3]"));

        Assertions.assertEquals("jdoe", credentialUsername.getText());
        Assertions.assertEquals("www.udacity.com", credentialURL.getText());
    }

    public void deleteCredential() throws InterruptedException {
        Thread.sleep(1000);
        this.credentialTab.click();
        WebElement deleteButton = this.webDriver.findElement(By.xpath("//*[@id='credentialTable']/tbody/tr/td[1]/a"));
        this.wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButton.click();
    }

    public void ifCredentialDeleted() throws InterruptedException {
        Thread.sleep(1000);
        this.credentialTab.click();

        List rows = webDriver.findElements(By.xpath("//*[@id='credentialTable']/tbody/tr"));
        Assertions.assertEquals(0, rows.size());

    }
}
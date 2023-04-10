package com.udacity.jwdnd.course1.cloudstorage.request;

public class NotesForm {

    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private Integer userId;

    // Constructor
    public NotesForm(Integer noteId, String noteTitle, String noteDescription, Integer userId) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userId = userId;
    }

    // Getter and Setter methods
    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Integer getUserId() {
        return userId;
    }

    // Override toString() method
    @Override
    public String toString() {
        return "Note Id: " + this.noteId +
               " Title: " + this.noteTitle +
               " Description: " + this.noteDescription +
               " User Id: "+ this.userId;
    }
}

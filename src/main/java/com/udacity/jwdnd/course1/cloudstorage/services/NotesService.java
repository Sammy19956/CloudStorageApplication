package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    private NotesMapper notesMapper;

    // Constructor
    public NotesService(NotesMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    public List<Notes> getAllNotes(Integer userId) {
        return notesMapper.selectAllNotes(userId);
    }

    public Integer saveNote(Integer noteId, String noteTitle,
                            String noteDescription, Integer userId) {
        Integer resultNoteId;

        if (noteId == null) {
            // Insert if current noteId is empty
            Notes notes = new Notes(0, noteTitle, noteDescription, userId);
            resultNoteId = notesMapper.saveNote(notes);
        } else {
            // Update if current noteId has value
            Notes notes = new Notes(noteId, noteTitle, noteDescription, userId);
            resultNoteId = notesMapper.updateNote(notes);
        }
        return resultNoteId;
    }

    public void deleteNote(Integer noteId) {
        notesMapper.deleteNote(noteId);
    }

}
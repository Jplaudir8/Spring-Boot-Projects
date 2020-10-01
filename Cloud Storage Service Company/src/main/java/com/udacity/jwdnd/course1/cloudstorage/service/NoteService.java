package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.modalForms.NoteModalForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;
    private UserMapper userMapper;

    public NoteService(NoteMapper noteMapper, UserMapper userMapper) {
        this.noteMapper = noteMapper;
        this.userMapper = userMapper;
    }

    public void createNote(NoteModalForm noteModalForm, String username) {
        Note newNote = new Note(null, noteModalForm.getNoteTitle(),
                noteModalForm.getNoteDescription(), userMapper.getUserIdByUsername(username));
        noteMapper.insertNote(newNote);
    }

    public void updateNote(NoteModalForm noteModalForm, String username) {
        Note selectedNote = new Note(Integer.parseInt(noteModalForm.getNoteId()),
                noteModalForm.getNoteTitle(), noteModalForm.getNoteDescription(),
                userMapper.getUserIdByUsername(username));
        noteMapper.updateNote(selectedNote);
    }

    public void deleteNote(int noteId) {
        noteMapper.deleteNoteById(noteId);
    }

    public List<Note> getAllUserNotes(int userId) {
        return noteMapper.getAllUserNotes(userId);
    }

}

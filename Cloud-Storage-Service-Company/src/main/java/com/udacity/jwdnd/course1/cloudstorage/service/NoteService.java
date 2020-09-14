package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteModalForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;
    private UserMapper userMapper;
    private UserService userService;

    public NoteService(NoteMapper noteMapper, UserMapper userMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    public void createNote(NoteModalForm noteModalForm, String username) {
        int userId = userService.getUser(username).getUserId();
        Note newNote = new Note();
        newNote.setNoteTitle(noteModalForm.getNoteTitle());
        newNote.setNoteDescription(noteModalForm.getNoteDescription());
        newNote.setUserId(userMapper.getUserIdByUsername(username));
        noteMapper.insertNote(newNote);
    }

    public void updateNote(NoteModalForm noteModalForm, String username) {
        Note selectedNote = new Note();
        selectedNote.setNoteId(Integer.parseInt(noteModalForm.getNoteId()));
        selectedNote.setNoteTitle(noteModalForm.getNoteTitle());
        selectedNote.setNoteDescription(noteModalForm.getNoteDescription());
        selectedNote.setUserId(userMapper.getUserIdByUsername(username));
        noteMapper.updateNote(selectedNote);
    }

    public List<Note> getAllUserNotes(int userId) {
        return noteMapper.getAllUserNotes(userId);
    }

}

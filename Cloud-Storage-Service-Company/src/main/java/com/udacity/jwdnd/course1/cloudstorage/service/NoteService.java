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

    public NoteService(NoteMapper noteMapper, UserMapper userMapper) {
        this.noteMapper = noteMapper;
        this.userMapper = userMapper;
    }

    public void addNote(NoteModalForm noteModalForm, String username) {
        Note newNote = new Note();
        newNote.setNoteTitle(noteModalForm.getNoteTitle());
        newNote.setNoteDescription(noteModalForm.getNoteDescription());
        //our 'Notes' table needs the UserId which is a foreign key
        // 1 way could be:
        newNote.setUserId(userMapper.getUserIdByUsername(username));
        noteMapper.insertNote(newNote);
    }

    public List<Note> getAllNotes() {
        return noteMapper.getAllNotes();
    }

}

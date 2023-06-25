package com.example.restservice.services;

import com.example.restservice.models.Note;
import com.example.restservice.repositories.INoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteService implements INoteService{
    @Autowired
    private INoteRepository _noteRepository;

    public List<Note> getLatestNotes(int count) throws Exception {
        return _noteRepository.getLatestNotes(count);
    }

}

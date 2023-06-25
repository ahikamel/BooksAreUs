package com.example.restservice.services;

import com.example.restservice.models.Note;
import java.util.List;

public interface INoteService {
    List<Note> getLatestNotes(int count) throws Exception;
}

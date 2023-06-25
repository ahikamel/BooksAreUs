package com.example.restservice.repositories;

import com.example.restservice.models.Note;
import java.util.List;

public interface INoteRepository {
    List<Note> getLatestNotes(int count) throws Exception;
}

package com.example.restservice.repositories;

import com.example.restservice.models.Note;
import com.example.restservice.repositories.utils.RepositoryUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Repository
public class NoteRepository implements INoteRepository {
    private final RepositoryUtils repoUtil;
    private final String _notesDataFilePath = "./src/main/resources/data/notes-data.json";
    private final String _dbName = "notes";
    private List<Note> _notesList;

    public NoteRepository(RepositoryUtils repoUtil) {
        this.repoUtil = repoUtil;
    }

    public List<Note> getLatestNotes(int count) throws Exception {
        checkNotesDbConnectivity();
        _notesList.sort((note1, note2) -> note2.date.compareTo(note1.date));
        return _notesList.subList(0, Math.min(count, _notesList.size()));
    }

    private boolean checkNotesDbConnectivity() throws Exception {
        Type NOTE_TYPE = new TypeToken<List<Note>>(){}.getType();
        _notesList = repoUtil.checkDbConnectivity(_notesList, _dbName, _notesDataFilePath, NOTE_TYPE);
        return true;
    }

}

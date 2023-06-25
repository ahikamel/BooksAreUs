package com.example.restservice.controllers;

import com.example.restservice.models.Note;
import com.example.restservice.services.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})   // TODO: Move local host concrete address to a configuration file
@RestController
@RequestMapping("/note")
public class NotesController {

    @Autowired
    private INoteService _noteService;

    @GetMapping("/latest")
    public ResponseEntity<List<Note>> getLatestNotes(@RequestParam(name = "count") int count) {
        try{
            List<Note> latestNotes = _noteService.getLatestNotes(count);
            if (latestNotes == null)
                throw new Exception();
            if (latestNotes.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            else
                return ResponseEntity.status(HttpStatus.OK).body(latestNotes);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

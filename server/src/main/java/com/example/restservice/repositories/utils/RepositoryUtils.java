package com.example.restservice.repositories.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.springframework.stereotype.Component;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class RepositoryUtils {

    // Wouldn't pass db as a parameter, just for simulation and avoiding repetitions.
    public <T> List<T> checkDbConnectivity(List<T> db, String dbName, String path, Type type) throws Exception {
        if (db == null){
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
            try{
                JsonReader reader = new JsonReader(new FileReader(path));
                db = gson.fromJson(reader, type);
                return db;
            }
            catch (IOException e){
                e.printStackTrace();
                throw new Exception("No connection to " + dbName + " db.");
            }
            // No need for 'finally' block because JsonReader and FileReader implement the Closeable interface, which extends AutoCloseable.
        }
        return db;
    }

    private static class LocalDateAdapter extends TypeAdapter<LocalDate> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        @Override
        public void write(JsonWriter out, LocalDate value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(formatter.format(value));
            }
        }

        @Override
        public LocalDate read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            } else {
                String dateStr = in.nextString();
                return LocalDate.parse(dateStr, formatter);
            }
        }
    }
}

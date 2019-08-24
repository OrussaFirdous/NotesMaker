package com.example.androidarchitecturecomponents;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao                           //Dao has to be either interface or abstract classes
public interface NoteDao {
    @Insert
    void insert(Note note);  //we r just defining method name and then we annotate it with the corresponding database operation
    //annotate means code runs faster
    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();
}

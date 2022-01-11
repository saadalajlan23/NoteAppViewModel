package com.example.noteappviewmodel

import androidx.lifecycle.LiveData
import androidx.room.*



@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Query("SELECT * FROM Note ORDER BY id ASC")
    fun getNotes(): LiveData<List<Note>>

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}
package com.example.noteappviewmodel

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "Note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val noteText: String)

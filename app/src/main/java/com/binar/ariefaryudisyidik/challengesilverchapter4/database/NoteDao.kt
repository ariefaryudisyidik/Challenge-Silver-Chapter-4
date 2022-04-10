package com.binar.ariefaryudisyidik.challengesilverchapter4.database

import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNote(note: Note): Long

    @Update
    fun updateNote(note: Note): Int

    @Delete
    fun deleteNote(note: Note): Int

    @Query("SELECT * FROM note ORDER BY id ASC")
    fun getAllNotes(): List<Note>
}
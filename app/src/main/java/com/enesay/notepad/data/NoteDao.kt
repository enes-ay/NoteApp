package com.enesay.notepad.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.enesay.notepad.model.Notes

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(note: Notes)

    @Query("delete from Notes")
    suspend fun deleteAllNotes()

    @Query("Select * from Notes")
    fun getNotes():LiveData<List<Notes>>

    @Query("update Notes set isDone=:isDone where noteId=:noteId ")
    suspend fun changeDone(isDone:Boolean,noteId:Int)
}
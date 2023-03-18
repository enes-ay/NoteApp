package com.enesay.notepad.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.enesay.notepad.model.Notes

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(note: Notes)
    @Update
    suspend fun updateNote(note: Notes)
    @Query("delete from Notes")
    suspend fun deleteAllNotes()

    @Query("Select * from Notes")
    fun getNotes():LiveData<List<Notes>>

    @Query("Select * from Notes where noteId=:noteId")
    fun getNote(noteId: Int):LiveData<Notes>

    @Query("update Notes set isDone=:isDone where noteId=:noteId ")
    suspend fun changeDone(isDone:Boolean,noteId:Int)
}
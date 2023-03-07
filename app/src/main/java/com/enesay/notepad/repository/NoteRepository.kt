package com.enesay.notepad.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.viewModelFactory
import com.enesay.notepad.data.NoteDao
import com.enesay.notepad.data.NoteDB
import com.enesay.notepad.model.Notes
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val dao:NoteDao
)
{

     fun getNotes():LiveData<List<Notes>>{
         return dao.getNotes()
    }
    suspend fun insertNote(note: Notes){
        dao.insertNote(note)
    }
    suspend fun markDone(isdDone:Boolean,noteId:Int){
        dao.changeDone(isdDone,noteId)

    }
    suspend fun deleteAllNotes(){
        dao.deleteAllNotes()
    }
}

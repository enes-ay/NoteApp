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
    fun getNote(noteId: Int):LiveData<Notes>{
        return dao.getNote(noteId)
    }
    suspend fun insertNote(duty: String){
        val note=Notes(0,"samples",duty,false)
        dao.insertNote(note)
    }
    suspend fun markDone(isDone:Boolean,noteId:Int){
        dao.changeDone(isDone,noteId)

    }
    suspend fun deleteAllNotes(){
        dao.deleteAllNotes()
    }
    suspend fun updateNote(noteId:Int,duty:String){
        var note=Notes(noteId,"sample",duty,false)
        dao.updateNote(note)
    }

    fun searchNotes(text:String):LiveData<List<Notes>>{
      return  dao.searchTodo(text)
    }
}

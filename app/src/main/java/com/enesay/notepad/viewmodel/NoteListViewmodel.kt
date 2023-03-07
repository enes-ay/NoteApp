package com.enesay.notepad.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesay.notepad.model.Notes
import com.enesay.notepad.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewmodel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {
    var noteList = repository.getNotes()

    fun getNotes() {
        noteList = repository.getNotes()
    }

    fun insertNote(note: Notes) = viewModelScope.launch {
        repository.insertNote(note)

    }

     fun deleteAllNotes()=viewModelScope.launch {
        repository.deleteAllNotes()
    }

    fun markDone(isDone: Boolean, noteId: Int) = viewModelScope.launch {
        repository.markDone(isDone, noteId)
        println("markDone çalıştı")
    }

}
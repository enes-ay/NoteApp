package com.enesay.notepad.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.enesay.notepad.R
import com.enesay.notepad.model.Notes
import com.enesay.notepad.repository.NoteRepository
import com.enesay.notepad.util.go

import com.enesay.notepad.view.NoteListFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class NoteDetailViewmodel @Inject constructor(
    val noteRepository: NoteRepository
) : ViewModel() {


    fun updateNote(noteId:Int,duty:String)=viewModelScope.launch {
        noteRepository.updateNote(noteId,duty)
    }
}
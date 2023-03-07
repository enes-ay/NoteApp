package com.enesay.notepad.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesay.notepad.R
import com.enesay.notepad.adapter.NoteListAdapter
import com.enesay.notepad.databinding.AddscreenBinding
import com.enesay.notepad.databinding.FragmentNoteListBinding
import com.enesay.notepad.model.Notes
import com.enesay.notepad.viewmodel.NoteListViewmodel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


class NoteListFragment : Fragment(R.layout.fragment_note_list) {
    private lateinit var binding: FragmentNoteListBinding
    private lateinit var viewmodel: NoteListViewmodel
    private lateinit var noteAdapter: NoteListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNoteListBinding.bind(view)
        viewmodel = ViewModelProvider(requireActivity()).get(NoteListViewmodel::class.java)


        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        noteAdapter = NoteListAdapter(viewmodel)
        binding.recyclerView.adapter = noteAdapter


        viewmodel.getNotes()
        observeLiveData()

        binding.fabAddNote.setOnClickListener {
            createDialog(requireContext())
        }

    }

    private fun observeLiveData() {
        viewmodel.noteList.observe(viewLifecycleOwner, Observer {

            noteAdapter.noteList = it
        })
    }

    private fun createDialog(context: Context) {
        val DialogView = LayoutInflater.from(context).inflate(R.layout.addscreen, null)
        val Builder = AlertDialog.Builder(context).setView(DialogView).show()
        Builder.setCancelable(false)

        val saveButton = DialogView.findViewById<Button>(R.id.btn_save)
        val cancelButton = DialogView.findViewById<Button>(R.id.btn_cancel)
        val textEdt = DialogView.findViewById<AppCompatEditText>(R.id.edt_toDo)


        saveButton.setOnClickListener {
            val text = textEdt.text.toString()

            if (text.isEmpty()) {
                Toast.makeText(context, "please type your note", Toast.LENGTH_SHORT).show()
            } else {
                viewmodel.insertNote(Notes("Sample Notes", textEdt.text.toString(), false))
                Builder.dismiss()
            }
        }
        cancelButton.setOnClickListener {
            Builder.dismiss()
        }
    }

}
package com.enesay.notepad.view

import android.os.Bundle
import android.text.Editable
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavArgs
import androidx.navigation.Navigation
import com.enesay.notepad.R
import com.enesay.notepad.databinding.FragmentNoteDetailBinding
import com.enesay.notepad.databinding.FragmentNoteListBinding
import com.enesay.notepad.model.Notes
import com.enesay.notepad.util.go
import com.enesay.notepad.viewmodel.NoteDetailViewmodel
import com.enesay.notepad.viewmodel.NoteListViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {
    private lateinit var viewmodel: NoteDetailViewmodel
    private lateinit var binding: FragmentNoteDetailBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_note_detail, container, false)
        binding.noteDetailFragment = this
        arguments?.let {
            binding.note = NoteDetailFragmentArgs.fromBundle(it).note
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_update)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        viewmodel = ViewModelProvider(requireActivity()).get(NoteDetailViewmodel::class.java)
        toolbar.title="Note Update"

    }

    fun updateNote(noteId: Int, duty: String) {
        viewmodel.updateNote(noteId, duty)
        Navigation.go(
            requireView(),
            NoteDetailFragmentDirections.actionNoteDetailFragmentToNoteListFragment()
        )
    }

}
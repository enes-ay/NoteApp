package com.enesay.notepad.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesay.notepad.R
import com.enesay.notepad.view.adapter.NoteListAdapter
import com.enesay.notepad.databinding.FragmentNoteListBinding


class NoteListFragment : Fragment(R.layout.fragment_note_list), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentNoteListBinding
    private lateinit var viewmodel: NoteListViewmodel
    private lateinit var noteAdapter: NoteListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNoteListBinding.bind(view)
        viewmodel = ViewModelProvider(requireActivity()).get(NoteListViewmodel::class.java)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarHome)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        noteAdapter = NoteListAdapter(viewmodel)
        binding.recyclerView.adapter = noteAdapter

        viewmodel.getNotes()
        observeLiveData()
        setToolbar()
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
                viewmodel.insertNote(textEdt.text.toString())
                Builder.dismiss()
            }
        }
        cancelButton.setOnClickListener {
            Builder.dismiss()
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
            viewmodel.searhNote(query!!)
            observeLiveData()
            return true

    }

    override fun onQueryTextChange(newText: String?): Boolean {

            viewmodel.searhNote(newText!!)
            observeLiveData()
            return true

    }
    private fun setToolbar() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.top_bar, menu)

                val item = menu.findItem(R.id.searchNotes)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@NoteListFragment)

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.searchNotes) {

                } else if (menuItem.itemId == R.id.deleteNotes) {
                    viewmodel.deleteAllNotes()
                    Toast.makeText(context, "deleted all notes", Toast.LENGTH_SHORT).show()
                }
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


}
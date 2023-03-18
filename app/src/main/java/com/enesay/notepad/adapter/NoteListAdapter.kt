package com.enesay.notepad.adapter

import android.app.Application
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.ViewModelInitializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.enesay.notepad.R
import com.enesay.notepad.databinding.FragmentNoteListBinding
import com.enesay.notepad.databinding.NoteRowBinding
import com.enesay.notepad.model.Notes
import com.enesay.notepad.util.go
import com.enesay.notepad.view.NoteListFragment
import com.enesay.notepad.view.NoteListFragmentDirections
import com.enesay.notepad.viewmodel.NoteListViewmodel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.currentCoroutineContext
import org.w3c.dom.Text
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class NoteListAdapter(var viewmodel: NoteListViewmodel) : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>(){

    class NoteViewHolder(var binding:NoteRowBinding):ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Notes>() {
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var noteList: List<Notes>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<NoteRowBinding>(inflater,R.layout.note_row,parent,false)

        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
    return  noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        for (i in noteList){
            println("id kontrol"+i.noteId)

        }
        val note=noteList.get(position)

        holder.binding.note=note

        holder.binding.checkedDone.setOnCheckedChangeListener { compoundButton, b ->
            if (compoundButton.isChecked) {
                viewmodel.markDone(true, noteList.get(position).noteId)

            } else {
                viewmodel.markDone(false, noteList.get(position).noteId)

            }

        }
        holder.itemView.setOnClickListener {
            Navigation.go(it,NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(note))

        }

    }

}
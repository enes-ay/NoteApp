package com.enesay.notepad.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.enesay.notepad.R
import com.enesay.notepad.databinding.NoteRowBinding
import com.enesay.notepad.model.Notes
import com.enesay.notepad.util.go
import com.enesay.notepad.view.NoteListFragmentDirections
import com.enesay.notepad.view.NoteListViewmodel

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
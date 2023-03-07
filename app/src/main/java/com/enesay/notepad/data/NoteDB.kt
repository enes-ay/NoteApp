package com.enesay.notepad.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.enesay.notepad.model.Notes

@Database(entities = [Notes::class], version = 1)
abstract class NoteDB:RoomDatabase() {

    abstract fun getDao(): NoteDao

}
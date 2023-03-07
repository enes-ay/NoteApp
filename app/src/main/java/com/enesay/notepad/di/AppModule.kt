package com.enesay.notepad.di

import android.content.Context
import androidx.room.Room
import com.enesay.notepad.data.NoteDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun injectDao(database: NoteDB)=database.getDao()

    @Singleton
    @Provides
    fun injectRoomDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, NoteDB::class.java, "NoteDB").build()
}
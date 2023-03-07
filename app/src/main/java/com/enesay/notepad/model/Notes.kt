package com.enesay.notepad.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notes(

    @ColumnInfo
    val name:String,
    @ColumnInfo
    val duty:String,
    @ColumnInfo(defaultValue = "false")
    val isDone:Boolean
) {
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    var noteId=0
}
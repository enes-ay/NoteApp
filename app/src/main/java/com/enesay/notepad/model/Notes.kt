package com.enesay.notepad.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class Notes(

    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    var noteId: Int,
    @ColumnInfo
    val name:String,
    @ColumnInfo
    val duty:String,
    @ColumnInfo(defaultValue = "false")
    val isDone:Boolean
):java.io.Serializable {

}
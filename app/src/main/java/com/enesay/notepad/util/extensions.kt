package com.enesay.notepad.util

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation


    fun Navigation.go(view:View,id:NavDirections) {
        findNavController(view).navigate(id)
    }


package com.example.examenib.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

class ChangeActivity {

    companion object{
        fun goActivity (clase: Class<*>, contex: Context){
            val intent = Intent(contex, clase)
            startActivity(contex, intent, null)
        }
    }
}
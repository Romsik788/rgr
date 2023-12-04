package com.roman.rgr

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseDatabase.getInstance("https://notes-21104-default-rtdb.europe-west1.firebasedatabase.app/").setPersistenceEnabled(true)
    }
}
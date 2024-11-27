package com.swordcatbreedsapplication

import android.app.Application
import com.swordcatbreedsapplication.data.local.CatBreedDatabase

//import dagger.hilt.android.HiltAndroidApp

//@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //
        //CatBreedDatabase.getDatabase(this)
    }
}
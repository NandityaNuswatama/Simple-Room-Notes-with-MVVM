package com.example.tugasqatros.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tugasqatros.ViewModelMain
import com.example.tugasqatros.di.Injection
import com.example.tugasqatros.repository.MainRepository

class ViewModelFactory(private val mainRepository: MainRepository): ViewModelProvider.NewInstanceFactory() {
    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ViewModelMain::class.java) -> {
                ViewModelMain(mainRepository) as T
            }
            else -> throw  Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
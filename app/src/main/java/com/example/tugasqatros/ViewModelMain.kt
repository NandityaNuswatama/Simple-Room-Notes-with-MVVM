package com.example.tugasqatros

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tugasqatros.model.EntityGoods
import com.example.tugasqatros.repository.MainRepository

class ViewModelMain(private val mainRepository: MainRepository): ViewModel() {
    fun getAllData(): LiveData<List<EntityGoods>>{
        return mainRepository.getAllData()
    }

    fun insert(data: EntityGoods){
        mainRepository.insert(data)
    }

    fun deleteById(id: Int){
        mainRepository.deleteById(id)
    }
}
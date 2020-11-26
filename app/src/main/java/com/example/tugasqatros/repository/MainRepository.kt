package com.example.tugasqatros.repository

import androidx.lifecycle.LiveData
import com.example.tugasqatros.model.EntityGoods
import com.example.tugasqatros.model.local.LocalDataSource

class MainRepository (private val localDataSource: LocalDataSource){

    companion object{
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(localDataSource: LocalDataSource): MainRepository = instance?:
        synchronized(this){
            instance?: MainRepository(localDataSource)
        }
    }

    fun getAllData(): LiveData<List<EntityGoods>>{
        return localDataSource.getAllData()
    }

    fun insert(data: EntityGoods){
        localDataSource.insert(data)
    }

    fun deleteById(id: Int) {
        localDataSource.deleteById(id)
    }
}
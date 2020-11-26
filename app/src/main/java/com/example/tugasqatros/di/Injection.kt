package com.example.tugasqatros.di

import android.content.Context
import com.example.tugasqatros.model.local.DatabaseGoods
import com.example.tugasqatros.model.local.LocalDataSource
import com.example.tugasqatros.repository.MainRepository

object Injection {
    fun provideRepository(context: Context): MainRepository{
        val database = DatabaseGoods.getDatabase(context)

        val localRepository = LocalDataSource.getInstance(database.goodsDao())

        return MainRepository.getInstance(localRepository)
    }
}
package com.example.tugasqatros.model.local

import androidx.lifecycle.LiveData
import com.example.tugasqatros.model.EntityGoods
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalDataSource (private val goodsDao: GoodsDao){
    companion object{
        private var instance: LocalDataSource?= null

        fun getInstance(goodsDao: GoodsDao): LocalDataSource = instance ?: LocalDataSource(goodsDao)
    }

    fun getAllData(): LiveData<List<EntityGoods>>{
        return goodsDao.getAllData()
    }

    fun insert(data: EntityGoods){
        CoroutineScope(Dispatchers.IO).launch {
            goodsDao.insert(data)
        }
    }

    fun deleteById(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            goodsDao.deleteById(id)
        }
    }
}
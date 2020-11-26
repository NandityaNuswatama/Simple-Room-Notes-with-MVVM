package com.example.tugasqatros.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tugasqatros.model.EntityGoods

@Dao
interface GoodsDao {
    @Query("SELECT * from goods_table ORDER BY id DESC")
    fun getAllData(): LiveData<List<EntityGoods>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: EntityGoods): Long

    @Query("DELETE FROM goods_table WHERE id =:id")
    suspend fun deleteById(id: Int)
}
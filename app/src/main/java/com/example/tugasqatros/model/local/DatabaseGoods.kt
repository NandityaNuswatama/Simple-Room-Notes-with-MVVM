package com.example.tugasqatros.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tugasqatros.model.EntityGoods

@Database(entities = [EntityGoods::class], version = 1, exportSchema = false)
abstract class DatabaseGoods : RoomDatabase(){
    abstract fun goodsDao(): GoodsDao

    companion object{
        @Volatile
        private var INSTANCE: DatabaseGoods? = null

        @JvmStatic
        fun getDatabase(context: Context): DatabaseGoods {
            if(INSTANCE == null){
                synchronized(DatabaseGoods::class.java) {
                    if(INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            DatabaseGoods::class.java,
                            "goods_table"
                        ).build()
                    }
                }
            }
            return INSTANCE as DatabaseGoods
        }
    }
}
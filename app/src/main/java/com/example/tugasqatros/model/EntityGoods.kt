package com.example.tugasqatros.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goods_table")
class EntityGoods (
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "supplier") var supplier: String = "",
    @ColumnInfo(name = "goods") var goods: String = "",
    @ColumnInfo(name = "time") var time: String = ""
)
package com.example.myphone.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myphone.pojo.PhoneItem
import com.example.myphone.pojo.detailsItem

private const val DATA_BASE_NAME = "products_db"

@Database(entities = [PhoneItem::class, detailsItem::class],version = 1)

abstract class ProductsDB : RoomDatabase() {
    abstract fun getPhoneDao() : PhoneDao
    abstract fun getDetailsDao() : DetailsDao

    companion object {
        @Volatile
        private var INSTANCE: ProductsDB? = null

        fun getDataBase(context: Context): ProductsDB {

            val tempInterface = INSTANCE
            if(tempInterface != null) {
                return tempInterface
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context,
                ProductsDB::class.java,
                DATA_BASE_NAME)
                    .build()

                INSTANCE = instance
                return instance
            }

        }
    }
}


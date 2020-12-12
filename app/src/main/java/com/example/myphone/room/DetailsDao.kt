package com.example.myphone.room

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myphone.pojo.detailsItem

interface DetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllData(mProductsDB: List<detailsItem>)

    @Query("SELECT * FROM details_table")
    fun getAllData(): LiveData<List<detailsItem>>

    @Query("SELECT * FROM details_table WHERE id=:mID")
    fun getOne(mID: Int): LiveData<detailsItem>


}
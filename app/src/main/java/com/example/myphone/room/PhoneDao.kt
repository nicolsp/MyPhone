package com.example.myphone.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myphone.pojo.PhoneItem
@Dao
interface PhoneDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllData(mProductsDB: List<PhoneItem>)

    @Query("SELECT * FROM phone_table")
    fun getAllData(): LiveData<List<PhoneItem>>

    @Query("SELECT * FROM phone_table WHERE id=:mID")
    fun getOne(mID: Int): LiveData<PhoneItem>

   // @Update
    //suspend fun updatePhone()
}
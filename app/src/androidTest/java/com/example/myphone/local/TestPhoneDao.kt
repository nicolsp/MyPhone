package com.example.myphone.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.myphone.room.PhoneDao
import org.junit.Before
import org.junit.Rule

class TestPhoneDao {

    @get:Rule

    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mPhoneDao: PhoneDao
  //  private lateinit var db: PhoneDB

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
       // db = Room.inMemoryDatabaseBuilder(context,PhoneDB::cla)
    }


}
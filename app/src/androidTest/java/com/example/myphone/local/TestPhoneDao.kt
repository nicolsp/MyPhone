package com.example.myphone.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.myphone.pojo.PhoneItem
import com.example.myphone.room.PhoneDao
import com.example.myphone.room.ProductsDB
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestPhoneDao {

    @get:Rule

    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mPhoneDao: PhoneDao
    private lateinit var db: ProductsDB

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context,ProductsDB::class.java).build()
        mPhoneDao = db.getPhoneDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertListElemento_happy_case() = runBlocking {
        val phonList = listOf(PhoneItem(1,"asd","awdds",3))

        mPhoneDao.insertAllData(phonList)

        mPhoneDao.getAllData().observeForever {
            assertThat(it).isNotEmpty()
            assertThat(it[0].id).isEqualTo(1)
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun obtainInPhoneByID() = runBlocking {
        val id = 1
        val phoneList = listOf(PhoneItem(1,"ololo","lalal",434))

        mPhoneDao.insertAllData(phoneList)
        mPhoneDao.getOne(id).observeForever {
            assertThat(it.id).isEqualTo(id)
        }
    }

}
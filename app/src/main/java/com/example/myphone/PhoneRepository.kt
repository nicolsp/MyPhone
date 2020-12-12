package com.example.myphone

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.myphone.pojo.detailsItem
import com.example.myphone.retrofit.DetailsList
import com.example.myphone.retrofit.Phone
import com.example.myphone.retrofit.RetrofitClient
import com.example.myphone.room.DetailsDao
import com.example.myphone.room.PhoneDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhoneRepository (private val mPhoneDao: PhoneDao,private val mDetailsDao: DetailsDao) {
    private val service = RetrofitClient.getRetrofitClient()

    val mLiveData = mPhoneDao.getAllData()
    val mLiveDetails = mDetailsDao.getAllData()

    fun getDataFromPhone() {
        val call =service.getDataFromApi()
        call.enqueue(object : Callback<Phone> {
            override fun onResponse(call: Call<Phone>, response: Response<Phone>) {
                when(response.code()) {

                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let {
                            mPhoneDao.insertAllData(it)
                        }
                    }
                    in 300..399 -> Log.d("ERROR 300",response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<Phone>, t: Throwable) {
                Log.e("REPOSITORY",t.message.toString())
            }


        })
    }

    fun getDataFromDetails() {
         val call2 = service.getDataFromDeta()
        call2.enqueue(object : Callback<DetailsList> {
            override fun onResponse(call: Call<DetailsList>, response: Response<DetailsList>) {
                when(response.code()) {
                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let {
                            mDetailsDao.insertAllData(it)
                        }
                    }
                    in 300..399 ->Log.d("ERROR 300", response.body().toString())
                }
            }

            override fun onFailure(call: Call<DetailsList>, t: Throwable) {
              Log.e("REPOSITORY",t.message.toString())
            }


        })
    }
    fun getOne(mID: Int): LiveData<detailsItem> {
        return mDetailsDao.getOne(mID)
    }



}
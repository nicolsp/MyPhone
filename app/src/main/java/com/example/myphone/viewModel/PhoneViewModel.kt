package com.example.myphone.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myphone.PhoneRepository
import com.example.myphone.pojo.PhoneItem
import com.example.myphone.pojo.detailsItem
import com.example.myphone.room.ProductsDB
import java.lang.Appendable

class PhoneViewModel( application: Application): AndroidViewModel(application) {

    private  val mRepository : PhoneRepository

    val mAllPhone : LiveData<List<PhoneItem>>

    val mAllDetails : LiveData<List<detailsItem>>

    init {
        val mPhoneDAO = ProductsDB.getDataBase(application).getPhoneDao()
        val mDetailsDAO = ProductsDB.getDataBase(application).getDetailsDao()

        mRepository = PhoneRepository(mPhoneDAO,mDetailsDAO)

        mAllPhone = mRepository.mLiveData
        mAllDetails = mRepository.mLiveDetails

        mRepository.getDataFromPhone()
        mRepository.getDataFromDetails()
    }

    fun getOneGo(mID: Int): LiveData<detailsItem> {
        return mRepository.getOne(mID)
    }
}
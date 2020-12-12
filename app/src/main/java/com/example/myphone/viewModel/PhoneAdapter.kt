package com.example.myphone.viewModel

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.myphone.R
import com.example.myphone.pojo.PhoneItem
import kotlinx.android.synthetic.main.itemphone_list.view.*

class PhoneAdapter( var mPhoneID: PhoneID ) : RecyclerView.Adapter<PhoneAdapter.mViewHolder> () {
    private var mdetList = emptyList<PhoneItem>()

    fun updateListPhone(mDetList: List<PhoneItem>) {
        mdetList = mDetList
        notifyDataSetChanged()
    }

    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mPhoneimg = itemView.imgPhone
        val mName = itemView.txtname
        val mId = itemView.txtid
        val mPhonePrice = itemView.txtPrice

        val itemView = itemView.setOnClickListener {
            mPhoneID.passData(mdetList[adapterPosition])
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemphone_list,parent,false)
        return mViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        val mPhoneItem: PhoneItem = mdetList[position]

        holder.mId.text = mPhoneItem.id.toString()
        holder.mName.text = mPhoneItem.name.toString()
        holder.mPhonePrice.text = mPhoneItem.price.toString()

        //mefalta una parte
        Glide.with(holder.itemView.context)
            .load(mPhoneItem.image)
            .transform(CenterCrop(),RoundedCorners(20))
            .into(holder.mPhoneimg)

    }

    override fun getItemCount() = mdetList.size

    interface  PhoneID{
        fun passData(mPhoneID: PhoneItem)
    }

}
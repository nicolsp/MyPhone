package com.example.myphone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.myphone.pojo.detailsItem
import com.example.myphone.viewModel.PhoneViewModel
import kotlinx.android.synthetic.main.fragment_second.*


 class SecondFragment : Fragment() {
    lateinit var mViewModel : PhoneViewModel
    lateinit var phone: detailsItem
    var mId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(PhoneViewModel::class.java)
        arguments?.let {
            mId = it.getInt("id",1)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            mViewModel.getOneGo(mId).observe(viewLifecycleOwner, Observer {

                if(it != null ) {

                    txtname2.setText(it.name)
                    txtdes2.setText(it.description)
                    txtPrice2.setText(it.price.toString())
                    txtlasp2.setText(it.lastPrice.toString())
                    txtcred2.setText(it.credit.toString())

                    Glide.with(this).load(it.image).transform(CenterCrop(), RoundedCorners(20))
                            .into(imgdetails)
                }
            })
        

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}
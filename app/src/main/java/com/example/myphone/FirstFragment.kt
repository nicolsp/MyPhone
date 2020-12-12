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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myphone.pojo.PhoneItem
import com.example.myphone.viewModel.PhoneAdapter
import com.example.myphone.viewModel.PhoneViewModel
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
 class FirstFragment : Fragment(), PhoneAdapter.PhoneID {
    lateinit var mViewModel: PhoneViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(PhoneViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mRecycler = recycler
        val mAdapter = PhoneAdapter(this)


        mRecycler.adapter = mAdapter
        mRecycler.layoutManager = LinearLayoutManager(context)

        mViewModel.mAllPhone.observe(viewLifecycleOwner, Observer {
            mAdapter.updateListPhone(it)
        })

       // view.findViewById<Button>(R.id.button_first).setOnClickListener {
         //   findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        //}
    }

   override fun passData(mPhoneID: Int ){
        val mBundle = Bundle()
        mBundle.putInt("id",mPhoneID)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,mBundle)
    }
}
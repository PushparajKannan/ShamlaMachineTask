package com.example.myapplication.home.detailedfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailedBinding

class DetailedFragment : Fragment() {

    private lateinit var mBinding: FragmentDetailedBinding

    private val args: DetailedFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detailed, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.model = args.model
        return mBinding.root
    }
}
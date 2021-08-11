package com.example.myapplication.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityHomeBinding
    private val navController: NavController by lazy {
        val activity = requireNotNull(this)
        activity.findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_home)


    }
}
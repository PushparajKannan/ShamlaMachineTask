package com.example.myapplication.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.home.HomeActivity

class MainActivity : AppCompatActivity() {

    private val mDelayHandler: Handler = Handler(Looper.getMainLooper())
    private val splashDelay: Long = 2000

    private lateinit var mBinding: ActivityMainBinding

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       mBinding =  DataBindingUtil.setContentView(this, R.layout.activity_main)

        mDelayHandler.postDelayed(mRunnable, splashDelay)
    }

    public override fun onDestroy() {
        mDelayHandler.removeCallbacks(mRunnable)
        super.onDestroy()
    }

}
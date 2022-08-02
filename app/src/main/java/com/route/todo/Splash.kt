package com.route.todo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class Splash:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        Handler(Looper.getMainLooper())
            .postDelayed({StartActivity()},2000)
    }
    fun StartActivity(){
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}
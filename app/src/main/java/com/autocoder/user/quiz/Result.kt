package com.autocoder.user.quiz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Result : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
    }
    fun back(view: View)
    {
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)

    }

}

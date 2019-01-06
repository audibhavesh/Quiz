package com.autocoder.user.quiz

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Toast
import com.autocoder.user.quiz.Controllers.DownloadController
import com.autocoder.user.quiz.Controllers.Plant
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var right:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        optionA.visibility=View.INVISIBLE
        optionB.visibility=View.INVISIBLE

        if(checkInternet())
        {
              val download=DownloadTask()
              download.execute()
        }
    }
    
    //option A button click listener
    fun optA(view: View){
        optionA.isEnabled=false
        optionB.isEnabled=false
        next.visibility=View.VISIBLE
    }
    //option B button click listener
    fun optB(view:View){

        optionA.isEnabled=false
        optionB.isEnabled=false
        next.visibility=View.VISIBLE

    }
    //Next button click listener
    fun next(view:View) {
        next.setText("Next")
        optionA.visibility=View.VISIBLE
        optionB.visibility=View.VISIBLE
        optionA.isEnabled = true
        optionB.isEnabled = true
        next.visibility = View.INVISIBLE
    }
    //Check for Internet Connection
    private fun checkInternet():Boolean
    {
        val conManager:ConnectivityManager=this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkinfo=conManager.activeNetworkInfo
        val state=networkinfo!=null&&networkinfo.isConnectedOrConnecting
        if(state)
        {
            return true
        }
        else {
            //calling a prompt function if network is not connected
            createNetworkErrorDialog()
            return false
        }
    }
    //Prompt a dialog box if internet is not connected
    private fun createNetworkErrorDialog()
    {
        val networkDialog:AlertDialog=AlertDialog.Builder(this@MainActivity).create()
        networkDialog.setTitle("Network Error")
        networkDialog.setMessage("Please check for Internet Connection")
        networkDialog.setButton(AlertDialog.BUTTON_POSITIVE,"Ok")
        {
                dialog: DialogInterface, which ->
                    startActivity(Intent(Settings.ACTION_SETTINGS))
        }
        networkDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Cancel")
        {
                dialog:DialogInterface, which ->
                Toast.makeText(this,"You must be connected to internet",Toast.LENGTH_SHORT).show()
                    finish()
        }
        networkDialog.show()
    }
    inner class DownloadTask:AsyncTask<String,Int,List<Plant>>(){
        override fun doInBackground(vararg params: String?): List<Plant>? {
            val dc= DownloadController()
             dc.DownloadJsonobject(applicationContext)
            return null
        }
    }
}

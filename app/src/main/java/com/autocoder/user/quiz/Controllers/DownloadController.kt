package com.autocoder.user.quiz.Controllers


import android.content.Context
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.Request;
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class DownloadController {
    lateinit var jsonArray:JSONArray
    lateinit var jsonObject:JSONObject
    var plants:ArrayList<Plant> = ArrayList()
    fun DownloadJsonobject(context:Context)
    {
        val queue:RequestQueue=Volley.newRequestQueue(context)

        val url="http://www.plantplaces.com/perl/mobile/flashcard.pl"
        val  http= JsonObjectRequest(Request.Method.GET,url,null,Response.Listener{
                response ->
            try {
                jsonArray = response.getJSONArray("values")
                var c=0
//                while (c < 5) {
                    var plant=Plant()
                    jsonObject=jsonArray.getJSONObject(c)
                    plant.genus=jsonObject.getString("genus")
                    plant.species=jsonObject.getString("species")
                    plant.plantimgName=jsonObject.getString("picture_name")
                    plants.add(plant)
                    c++
//                }

            }
            catch (e:Exception)
            {
                 e.printStackTrace()
            }
         },Response.ErrorListener {
             error ->


         })

        queue.add(http)
        
    }

   
}
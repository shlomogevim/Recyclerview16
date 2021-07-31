package com.sg.recyclerview16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sg.recyclerview16.model.Profile
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException



// ***************   https://www.youtube.com/watch?v=krtts-2jLLo  *****************
// the recyclerview ia stake

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rcv_content.setHasFixedSize(true)
        rcv_content.adapter=null



        val request = Request.Builder()
            .url("https://picsum.photos/v2/list?limit=5")
            .build()
        val okHttpClient = OkHttpClient()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("mmm", "Somthing go wrong")
            }

            override fun onResponse(call: Call, response: Response) {
                val profileArrayList = feachProfileArrayList(response)
                runOnUiThread {
                    val myAdapter = MyAdapter(profileArrayList)
                    rcv_content.adapter=myAdapter
                    myAdapter.notifyDataSetChanged()

                }
            }

        })
    }

    private fun feachProfileArrayList(response: Response): ArrayList<Profile> {
        val arraryList = ArrayList<Profile>()
        val jsonArray = JSONArray(response.body()?.string())
        for (item in 0..jsonArray.length()-1) {
            val jsonObject = jsonArray.getJSONObject(item)
            val photoUrl = jsonObject.get("download_url").toString()
            val author = jsonObject.get("author").toString()?:"No Name"
            arraryList += Profile(photoUrl, author)
        }
        return arraryList
    }




}


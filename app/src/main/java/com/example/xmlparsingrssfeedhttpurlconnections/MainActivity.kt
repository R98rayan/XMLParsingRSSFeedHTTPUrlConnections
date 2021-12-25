package com.example.xmlparsingrssfeedhttpurlconnections

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeeddemo.RVAdapter
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: RVAdapter

    var list = listOf<Apology>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain = findViewById(R.id.rvMain)
        rvAdapter = RVAdapter(list)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(this)

        parseRSS()
    }

    private fun parseRSS(){
        CoroutineScope(Dispatchers.IO).launch {
            val data = async {
                val parser = XmlParser()
                parser.parse()
            }.await()
            try{
                withContext(Dispatchers.Main){
                    rvAdapter.update(data)
                }
            }catch(e: java.lang.Exception){
                Log.d("MAIN", "Unable to get data")
            }
        }
    }
}
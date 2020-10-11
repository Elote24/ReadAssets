package com.elliot.readassets

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader

class MainActivity : AppCompatActivity() {
    private lateinit var textViewLastContent : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewLastContent = findViewById(R.id.textview_lasttext)

        val contents = readContents()
        val contentAdapter = ContentAdapter(clickListener)
        val recyclerAssets = findViewById<RecyclerView>(R.id.recycler_assets)
        recyclerAssets.adapter = contentAdapter
        recyclerAssets.layoutManager = LinearLayoutManager(this)

        contents?.apply {
            contentAdapter.setData(this)
        }
    }

    override fun onPause() {
        super.onPause()
        val sharedPreferences = getSharedPreferences("default", Activity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("last_text", textViewLastContent.text.toString())
            .apply()
    }

    private val clickListener =  View.OnClickListener { view ->
        val textView = view as TextView
        //Toast.makeText(this, textView.text, Toast.LENGTH_LONG).show()
        textViewLastContent.text = textView.text
    }

    private fun readContents() = assets.list("content")?.map {file ->
        assets.open("content/$file").bufferedReader().use(BufferedReader::readText)
    }?.toList()
}
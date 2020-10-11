package com.elliot.readassets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContentAdapter(val clickListener: View.OnClickListener) : RecyclerView.Adapter<ContentAdapter.ContentViewHolder>() {

    private val contents = mutableListOf<String>()

    fun setData(contents: List<String>) : Boolean {
        this.contents.clear()
        return this.contents.addAll(contents)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.layout_content, parent, false)
        return ContentViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val content = this.contents[position]
        holder.onBind(content)
    }

    override fun getItemCount(): Int {
        return this.contents.size
    }


    class ContentViewHolder (val view : View,  val clickListener: View.OnClickListener) : RecyclerView.ViewHolder(view){
        fun onBind(texto : String){
            val textView = view.findViewById<TextView>(R.id.textview_content)
            textView.setOnClickListener(clickListener)
            textView.text = texto
        }
    }

}
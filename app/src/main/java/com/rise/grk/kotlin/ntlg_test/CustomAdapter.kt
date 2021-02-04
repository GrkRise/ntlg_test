package com.rise.grk.kotlin.ntlg_test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter(private val dataSet : List<CourseInfo>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle : TextView
        val textViewCount : TextView
        val imageViewAva : ImageView

        init {
            textViewTitle = itemView.findViewById(R.id.title)
            textViewCount = itemView.findViewById(R.id.count)
            imageViewAva = itemView.findViewById(R.id.ava)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewTitle.text = dataSet[position].title
        holder.textViewCount.text = dataSet[position].count
        holder.imageViewAva.setBackgroundColor(dataSet[position].ava)
    }

    override fun getItemCount() = dataSet.size
}
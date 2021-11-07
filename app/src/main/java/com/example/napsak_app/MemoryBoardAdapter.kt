package com.example.napsak_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

// Activity Board 
class MemoryBoardAdapter(private val context: Context, private val numPieces: Int) :
    // overwrite ViewHolder
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


        // constructor
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // set the width and height of the cards
            val cardWidth = parent.width /2
            val cardHeight = parent.height /2

            // initialize the view
            val view = LayoutInflater.from(context).inflate(R.layout.memory_card, parent, false)

            // return ViewHolder
            return ViewHolder(view)
        }

        // This happens exactly in onBindViewHolder().
        // Initially you will get new unused view holders and you have to
        // fill them with data you want to display.
        // But as you scroll you'll start getting view holders
        // that were used for rows that went off screen
        // and you have to replace old data that they held with new data.
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            holder.bind(position)
            // No-op
        }

        override fun getItemCount() = numPieces
    }


private fun RecyclerView.ViewHolder.bind(position: Int) {

}


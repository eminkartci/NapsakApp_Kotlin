package com.example.napsak_app

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
//import com.rkpandey.mymemory.models.BoardSize
//import com.rkpandey.mymemory.models.MemoryCard
//import com.squareup.picasso.Picasso
import kotlin.math.min as min

// Activity Board 
class MemoryBoardAdapter(
    private val context: Context,
    private val BoardSize: BoardSize) :
    // overwrite ViewHolder
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        // Card Names are class names
        private const val TAG = "MemoryBoardAdapter"
        private const val MARGIN_SIZE = 10
    }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            // Creating image Button
            private val imageButton = itemView.findViewById<ImageButton>(R.id.imageButton)

            // Setting Button function
            fun bind(position: Int){
                imageButton.setOnClickListener {
                    Log.i(TAG, "Clicked on position $position")
                }
            }

        }

        // constructor
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // set the width and height of the cards
            val cardWidth = parent.width / boardSize.getWidth() - (2* MARGIN_SIZE)
            val cardHeight = parent.height / boardSize.getHeight() - (2 * MARGIN_SIZE)
            val cardSideLength: Int = min(cardWidth, cardHeight)


            // initialize the view
            val view = LayoutInflater.from(context).inflate(R.layout.memory_card, parent, false)
            val layoutParams = view.findViewById<CardView>(R.id.cardView).layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.width = cardSideLength
            layoutParams.height = cardSideLength
            layoutParams.setMargins(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE)
            // Set the appropriate width/height of the view
            return ViewHolder(view)

        }

        // This happens exactly in onBindViewHolder().
        // Initially you will get new unused view holders and you have to
        // fill them with data you want to display.
        // But as you scroll you'll start getting view holders
        // that were used for rows that went off screen
        // and you have to replace old data that they held with new data.
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            // BU FONKSİYONU TANIMIYOR
            //holder.bind(position)
        }

        override fun getItemCount() = boardSize.numCards
    }



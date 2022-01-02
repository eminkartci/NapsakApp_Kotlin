package com.example.napsak_app

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.napsak_app.models.BoardSize
import com.example.napsak_app.models.Event
import com.example.napsak_app.models.EventViewModel
import com.example.napsak_app.models.NapsakCard
//import com.rkpandey.mymemory.models.BoardSize
//import com.rkpandey.mymemory.models.MemoryCard
//import com.squareup.picasso.Picasso
import kotlin.math.min as min

// Activity Board 
class NapsakBoardAdapter(
    private val context: Context,
    private val BoardSize: BoardSize,
    private val events: List<Event>,
    private val cardClickListener: CardClickListener
) :
    // overwrite ViewHolder
    RecyclerView.Adapter<NapsakBoardAdapter.ViewHolder>() {

    private var eventList = emptyList<Event>();

    companion object {
        // Card Names are class names
        private const val TAG = "MemoryBoardAdapter"
        private const val MARGIN_SIZE = 10
    }

    interface CardClickListener {
        fun onCardClicked(position: Int)
    }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            // Creating image Button
            private val imageButton = itemView.findViewById<ImageButton>(R.id.imageButton)


            // Setting Button function
            fun bind(position: Int){
                if(eventList.size < 1){
                    val memoryCard = events[position]
                    // Checks the card face up then shows the corresponding image
                    imageButton.setImageResource(if (events[position].isFaceUp) memoryCard.eventImage else R.drawable.amean_logo)
                    imageButton.setOnClickListener {
                        Log.i(TAG, "Clicked on position $position")
                        cardClickListener.onCardClicked(position)
                    }
                }else{
                    val memoryCard = eventList[position]
                    // Checks the card face up then shows the corresponding image
                    imageButton.setImageResource(if (memoryCard.isFaceUp) memoryCard.eventImage else R.drawable.amean_logo)
                    imageButton.setOnClickListener {
                        Log.i(TAG, "Clicked on position $position")
                        cardClickListener.onCardClicked(position)
                    }
                }

            }

        }

        // constructor
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // set the width and height of the cards
            val cardWidth = parent.width / BoardSize.getWidth() - (2* MARGIN_SIZE)
            val cardHeight = parent.height / BoardSize.getHeight() - (2 * MARGIN_SIZE)
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

        override fun onBindViewHolder(holder: NapsakBoardAdapter.ViewHolder, position: Int) {
            holder.bind(position)
        }

        override fun getItemCount() = BoardSize.numCards

        fun setData(eventList: List<Event>){
            this.eventList = eventList
            notifyDataSetChanged()
        }
    }



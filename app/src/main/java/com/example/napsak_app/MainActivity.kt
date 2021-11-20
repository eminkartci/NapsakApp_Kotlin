
/*
Project: Napsak
Authors:
- Emin Kartci
- Metin Arda Koker
Date: 2 November 2021
GitHub: https://github.com/eminkartci/NapsakApp_Kotlin
 */
package com.example.napsak_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.napsak_app.models.BoardSize
import com.example.napsak_app.models.MemoryCard
import com.example.napsak_app.models.MemoryGame
import com.example.napsak_app.utils.DEFAULT_ICONS

// MainActivitiy is the first activated activitiy
class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    // lateinit: those variables will be initialized later because the instances are not created ye
    private lateinit var memoryGame: MemoryGame
    private lateinit var adapter: MemoryBoardAdapter

    private lateinit var rvBoard: RecyclerView
    private lateinit var tvNumMoves: TextView // number of Activities
    private lateinit var tvNumPairs: TextView // the matching percentage

    private var boardSize: BoardSize = BoardSize.EASY

    // onCreate is a default function
    override fun onCreate(savedInstanceState: Bundle?) {
        // call super constructor
        super.onCreate(savedInstanceState)
        // setContentView declares which xml is used for front-end
        setContentView(R.layout.activity_main)

        // initialize the variables
        rvBoard = findViewById(R.id.rvBoard)
        tvNumMoves = findViewById(R.id.tvNumMoves)
        tvNumPairs = findViewById(R.id.tvNumPairs)

        // The number of copies in here there is 3 copy because of less card drafts
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs()/2)
        val randomizedImages = (chosenImages + chosenImages + chosenImages).shuffled()
        // Defining memoryCards
        val memoryCards = randomizedImages.map{ MemoryCard(it) }

        val memoryGame = MemoryGame(boardSize)

        // Recycler view adapter: The Adapter provides access to the data items.
        adapter = MemoryBoardAdapter(this,boardSize,memoryCards, object: MemoryBoardAdapter.CardClickListener{
            override fun onCardClicked(position: Int) {
                Log.i(TAG, "Card clicked $position")
                updateWithFlip(position)
            }

            private fun updateWithFlip(position: Int) {
                memoryGame.flipCard(position)
                adapter.notifyDataSetChanged()
            }

        })
        rvBoard.adapter = adapter
        // Card numbers
        // to have constant sizes
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager = GridLayoutManager(this,2) // Column numbers
    }
}

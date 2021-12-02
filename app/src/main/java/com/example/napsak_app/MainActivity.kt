
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
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.napsak_app.databinding.ActivityMainBinding
import com.example.napsak_app.models.BoardSize
import com.example.napsak_app.models.NapsakCard
import com.example.napsak_app.models.MemoryGame
import com.example.napsak_app.models.User
import com.example.napsak_app.utils.DEFAULT_ICONS

// MainActivitiy is the first activated activitiy
class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    // lateinit: those variables will be initialized later because the instances are not created ye
    private lateinit var memoryGame: MemoryGame
    private lateinit var adapter: NapsakBoardAdapter

    private lateinit var rvBoard: RecyclerView
    private lateinit var tvNumMoves: TextView // number of Activities
    private lateinit var tvNumPairs: TextView // the matching percentage

    private var boardSize: BoardSize = BoardSize.EASY

    // onCreate is a default function
    override fun onCreate(savedInstanceState: Bundle?) {
        // call super constructor
        super.onCreate(savedInstanceState)
        // use data binding


        // setContentView declares which xml is used for front-end
        // BEFORE DATA BINDING
        // setContentView(R.layout.activity_main)
        // DATA BINDING
        val mainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        
        // Get user -> Hard Code Now
        val user: User = User("Emin Kartci",22, listOf("Coding","Reading","Guitar","Movie"),"91","12")
        // Bind the user
        mainBinding.user = user


        // initialize the variables
        rvBoard = findViewById(R.id.rvBoard)
        tvNumMoves = findViewById(R.id.matchingPercentage)
        tvNumPairs = findViewById(R.id.activityCount)

        // The number of copies in here there is 3 copy because of less card drafts
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs()/2)
        val randomizedImages = (chosenImages + chosenImages + chosenImages).shuffled()
        // Defining memoryCards
        val memoryCards = randomizedImages.map{ NapsakCard(it) }

        val memoryGame = MemoryGame(boardSize)

        // Recycler view adapter: The Adapter provides access to the data items.
        adapter = NapsakBoardAdapter(this,boardSize,memoryCards, object: NapsakBoardAdapter.CardClickListener{
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


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
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

// MainActivitiy is the first activated activitiy
class MainActivity : AppCompatActivity() {

    // lateinit: those variables will be initialized later because the instances are not created ye
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

        // Recycler view adapter: The Adapter provides access to the data items.
        rvBoard.adapter = MemoryBoardAdapter(this,boardSize) // Card numbers
        // to have constant sizes
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager = GridLayoutManager(this,2) // Column numbers
    }
}
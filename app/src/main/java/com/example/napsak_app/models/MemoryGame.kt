package com.example.napsak_app.models

import com.example.napsak_app.utils.DEFAULT_ICONS

class MemoryGame (private val boardSize: BoardSize) {


    val cards: List<MemoryCard>

    init {
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizedImages = (chosenImages + chosenImages).shuffled()
        cards = randomizedImages.map { MemoryCard(it) }
    }

    fun flipCard(position: Int) {
        val card = cards[position]
        card.isFaceUp = !card.isFaceUp
    }
}

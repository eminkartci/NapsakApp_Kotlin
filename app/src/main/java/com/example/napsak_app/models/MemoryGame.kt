package com.example.napsak_app.models

import com.example.napsak_app.utils.DEFAULT_ICONS

class MemoryGame (private val boardSize: BoardSize) {


    var events: List<Event>


    init {
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizedImages = (chosenImages + chosenImages).shuffled()


        events = randomizedImages.mapIndexed {
            index,image -> Event(index,false,"Event $index",image,index,index,index,index);
        }


    }

    fun flipCard(position: Int) {
        val event = events[position]
        event.isFaceUp = !event.isFaceUp
    }
}

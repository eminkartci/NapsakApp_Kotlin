package com.example.napsak_app.models

import com.example.napsak_app.utils.DEFAULT_ICONS

class MemoryGame (private val boardSize: BoardSize) {


    var events: List<Event>
    var eventListDB = emptyList<Event>()


    init {
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizedImages = (chosenImages + chosenImages).shuffled()


        events = randomizedImages.mapIndexed {
            index,image -> Event(index,false,"Event $index",image,index,index,index,index);
        }


    }

    fun flipCard(position: Int) {
        if(eventListDB.size < 9){
            val event = events[position]
            event.isFaceUp = !event.isFaceUp
        }else{
            val event = eventListDB[position]
            event.isFaceUp = !event.isFaceUp
        }

    }

    fun setEventList(eventList: List<Event>){
        this.eventListDB = eventList;
    }
}

package com.example.napsak_app.models

data class MemoryCard(
    // MemoryCard attributes
    val identifier: Int,
    // It will be changing while the card flipped
    var isFaceUp: Boolean = false

)
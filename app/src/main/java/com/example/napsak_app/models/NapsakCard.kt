package com.example.napsak_app.models

import java.io.Serializable

data class NapsakCard(
    // ID
    val eventID: Int,
    // MemoryCard attributes
    val identifier: Int,
    // It will be changing while the card flipped
    var isFaceUp: Boolean = false

):Serializable
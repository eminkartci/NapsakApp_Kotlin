package com.example.napsak_app.models


import java.io.Serializable


data class Event(
    val eventID:Int,
    val eventTitle:String,
    val eventImage: Int,
    val timePoint: Int,
    val socialPoint: Int,
    val physicalPoint: Int,
    val entertainmentPoint: Int
):Serializable
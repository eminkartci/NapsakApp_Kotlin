package com.example.napsak_app.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "event_table")
data class Event(
    @PrimaryKey(autoGenerate = true)
    val eventID:Int,
    var isFaceUp: Boolean = false,
    val eventTitle:String,
    val eventImage: Int,
    val timePoint: Int,
    val socialPoint: Int,
    val physicalPoint: Int,
    val entertainmentPoint: Int
):Serializable
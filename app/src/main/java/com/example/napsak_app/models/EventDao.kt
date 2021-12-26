package com.example.napsak_app.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPlant(event: Event)

    @Query(value = "SELECT * FROM event_table ORDER BY eventID ASC")
    fun readAllData(): LiveData<List<Event>>

}
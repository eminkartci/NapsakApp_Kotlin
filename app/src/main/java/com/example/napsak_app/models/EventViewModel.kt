package com.example.napsak_app.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EventViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Event>>
    private val repository: EventRepository
    init {
        val plantDao = EventDatabase.getDatabase(application).eventDao()
        repository = EventRepository(plantDao)
        readAllData = repository.readAllData

    }

    fun addEvent(event:Event){
        viewModelScope.launch (Dispatchers.IO){
            repository.addEvent(event);
        }
    }
}
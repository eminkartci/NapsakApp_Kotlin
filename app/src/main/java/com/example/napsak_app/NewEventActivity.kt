package com.example.napsak_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import com.example.napsak_app.databinding.ActivityMainBinding
import com.example.napsak_app.databinding.ActivityNewEventBinding

class NewEventActivity : AppCompatActivity() {

    // Variables
    private lateinit var cvNewImage : CardView
    private lateinit var ivNewImage : ImageView
    private lateinit var btnShare : Button
    private lateinit var etNewEventTitle : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Binding
        val newEventBinding: ActivityNewEventBinding = DataBindingUtil.setContentView(this,R.layout.activity_new_event)
        //setContentView(R.layout.activity_new_event)

        // Assign
        cvNewImage = newEventBinding.cvNewImage
        ivNewImage = newEventBinding.ivNewEventImage
        etNewEventTitle = newEventBinding.etEventTitle
        btnShare = newEventBinding.btnShareEvent

        

    }
}
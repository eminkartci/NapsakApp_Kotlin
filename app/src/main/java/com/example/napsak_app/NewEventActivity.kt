package com.example.napsak_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import com.example.napsak_app.databinding.ActivityMainBinding
import com.example.napsak_app.databinding.ActivityNewEventBinding

class NewEventActivity : AppCompatActivity() {

    companion object{
        private const val PICK_PHOTO_CODE = 655
    }

    // Variables
    private lateinit var cvNewImage : CardView
    private lateinit var ivNewImage : ImageView
    private lateinit var btnShare : Button
    private lateinit var etNewEventTitle : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // home icon show
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        // Binding
        val newEventBinding: ActivityNewEventBinding = DataBindingUtil.setContentView(this,R.layout.activity_new_event)
        //setContentView(R.layout.activity_new_event)

        // Assign
        cvNewImage = newEventBinding.cvNewImage
        ivNewImage = newEventBinding.ivNewEventImage
        etNewEventTitle = newEventBinding.etEventTitle
        btnShare = newEventBinding.btnShareEvent

        cvNewImage.setOnClickListener({
            val getImageIntent = Intent(Intent.ACTION_PICK)
            getImageIntent.type = "image/*"
            startActivityForResult(Intent.createChooser(getImageIntent,"Please select an event image."),PICK_PHOTO_CODE)
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home){
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}
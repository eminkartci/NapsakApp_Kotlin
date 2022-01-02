package com.example.napsak_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.napsak_app.databinding.ActivityNewEventBinding
import com.example.napsak_app.models.Event
import com.example.napsak_app.models.EventViewModel

class NewEventActivity : AppCompatActivity() {

    companion object{

    }

    // Variables
    private lateinit var btnShare : Button
    private lateinit var etNewEventTitle : EditText
    private lateinit var mEventViewModel: EventViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // home icon show
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.title = "New Event!"

        mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        // Binding
        val newEventBinding: ActivityNewEventBinding = DataBindingUtil.setContentView(this,R.layout.activity_new_event)

        // Assign
        etNewEventTitle = newEventBinding.etEventTitle
        btnShare = newEventBinding.btnShareEvent


        btnShare.setOnClickListener {
            insert2database(this,newEventBinding);
            finish();
        }

    }

    private fun insert2database(context: Context,binding:ActivityNewEventBinding){
        val eventTitle = binding.etEventTitle.text.toString()
        val pTime = binding.etTime.text.toString()
        val pEntertainment = binding.etEntertainment.text.toString()
        val pPhysical = binding.etPhysical.text.toString()
        val pSocial = binding.etSocial.text.toString()



        if(areValid(eventTitle,pTime,pEntertainment,pPhysical,pSocial)){

            val newEvent = Event(0, false, eventTitle, R.drawable.music, Integer.parseInt(pTime),Integer.parseInt(pEntertainment),Integer.parseInt(pPhysical),Integer.parseInt(pSocial));

            mEventViewModel.addEvent(newEvent)
            Toast.makeText(context,"Successfully Added!", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"Error on Insert!", Toast.LENGTH_LONG).show()
        }
    }

    private fun areValid(title:String,pEnt:String,pSoc:String,pTime:String,pPhysical:String):Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(pEnt) && TextUtils.isEmpty(pSoc) && TextUtils.isEmpty(pTime) && TextUtils.isEmpty(pPhysical) )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home){
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}
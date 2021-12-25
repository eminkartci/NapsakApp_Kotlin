package com.example.napsak_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import com.example.napsak_app.models.Event
import com.example.napsak_app.models.NapsakCard

class ActivitySelect : AppCompatActivity() {

    private lateinit var declineBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        // home icon show
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        declineBtn = findViewById(R.id.btn_decline)
        declineBtn.setOnClickListener {
            finish()
        }

        val tempActivity = intent.getSerializableExtra("activity") as Event
        Log.i("S Activity:", tempActivity.toString())
        //val tempActivity = Event(0,"Coding",R.drawable.cinema,5,4,3,2)

        // update the title
        supportActionBar?.title = tempActivity.eventTitle

        val detailsFragment = ActivityDetailsFragment()
        var detailData = Bundle()
        detailData.putSerializable("Event", tempActivity);
        detailsFragment.arguments = detailData

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment,detailsFragment)
            addToBackStack(null)
            commit()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home){
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
package com.example.napsak_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.example.napsak_app.models.Event
import com.example.napsak_app.models.NapsakCard
import android.content.ActivityNotFoundException
import android.content.Context

import android.content.Intent
import android.net.Uri


class ActivitySelect : AppCompatActivity() {

    private lateinit var declineBtn: Button
    private lateinit var acceptBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        // home icon show
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        declineBtn = findViewById(R.id.btn_decline)
        acceptBtn = findViewById(R.id.btn_accept)

        declineBtn.setOnClickListener {
            finish()
        }


        val tempActivity = intent.getSerializableExtra("activity") as Event
        Log.i("S Activity:", tempActivity.toString())
        //val tempActivity = Event(0,"Coding",R.drawable.cinema,5,4,3,2)

        acceptBtn.setOnClickListener {
            Toast.makeText(this, "${tempActivity.eventTitle} is added to your list!",Toast.LENGTH_LONG).show();
            if (tempActivity.eventImage == R.drawable.youtube){
                watchYoutubeVideo(this,"Uti2niW2BRA");
            }

            finish()
        }

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

    fun watchYoutubeVideo(context: Context, id: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
        val webIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("http://www.youtube.com/watch?v=$id")
        )
        try {
            context.startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            context.startActivity(webIntent)
        }
    }

}
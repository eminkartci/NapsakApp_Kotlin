package com.example.napsak_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.napsak_app.models.Event

class ActivitySelect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        val detailsFragment = ActivityDetailsFragment()
        val tempActivity = Event(0,"Coding",R.drawable.amean_logo,5,4,3,2)
        var detailData = Bundle()

        detailData.putSerializable("Event", tempActivity);

        detailsFragment.arguments = detailData

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment,detailsFragment)
            addToBackStack(null)
            commit()
        }

    }
}
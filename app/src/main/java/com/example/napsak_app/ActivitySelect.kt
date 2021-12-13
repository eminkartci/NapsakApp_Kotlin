package com.example.napsak_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ActivitySelect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        val detailsFragment = ActivityDetailsFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment,detailsFragment)
            addToBackStack(null)
            commit()
        }

    }
}
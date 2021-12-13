package com.example.napsak_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.napsak_app.databinding.FragmentActivityDetailsBinding
import com.example.napsak_app.models.Event


class ActivityDetailsFragment : Fragment(R.layout.fragment_activity_details) {

    private lateinit var detailsBinding: FragmentActivityDetailsBinding
    private lateinit var event: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var args: Bundle? = this.arguments;

        if(args != null){
            event = args.getSerializable("Event") as Event

            Log.i("Event Title:", event.eventTitle)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        detailsBinding = FragmentActivityDetailsBinding.inflate(inflater,container,false)

        detailsBinding.imEventImage.setImageResource(event.eventImage)

        detailsBinding.tvEventTitle.text = event.eventTitle

        detailsBinding.tvTime.text = "Time Point: " +event.timePoint.toString()
        detailsBinding.tvSocial.text = "Social Point: " +event.socialPoint.toString()
        detailsBinding.tvPhysical.text = "Physical Point: " +event.physicalPoint.toString()
        detailsBinding.tvEntertainment.text = "Entertainment Point: " +event.entertainmentPoint.toString()



        // Inflate the layout for this fragment
        return detailsBinding.root
    }


}
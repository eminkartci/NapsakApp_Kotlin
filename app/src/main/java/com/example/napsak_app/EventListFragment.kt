package com.example.napsak_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.napsak_app.databinding.FragmentActivityDetailsBinding
import com.example.napsak_app.databinding.FragmentEventListBinding
import com.example.napsak_app.models.BoardSize
import com.example.napsak_app.models.EventViewModel
import com.example.napsak_app.models.MemoryGame
import com.example.napsak_app.models.User


class EventListFragment : Fragment() {

    private lateinit var listBinding: FragmentEventListBinding

    private lateinit var memoryGame: MemoryGame
    private lateinit var adapter: NapsakBoardAdapter

    private lateinit var rvBoard: RecyclerView
    private lateinit var cvNewEvent: CardView // number of Activities
    private lateinit var cvRefresh: CardView // the matching percentage

    private lateinit var mEventViewModel: EventViewModel

    private var boardSize: BoardSize = BoardSize.EASY

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        listBinding = FragmentEventListBinding.inflate(inflater,container,false)

        // initialize the variables
        rvBoard = listBinding.rvBoard
        cvNewEvent = listBinding.cvNewEvent
        cvRefresh = listBinding.cvRefresh

        // set on click for refresh card view
        cvRefresh.setOnClickListener(refreshClickListener)
        cvNewEvent.setOnClickListener(newEventclickListener)

        setEvents()

        return listBinding.root
    }

    private val refreshClickListener: View.OnClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.cvRefresh -> {
                // Inform the user
                Toast.makeText(requireContext(),"New Events!!", Toast.LENGTH_SHORT).show()
                // update the events
                setEvents()
            }
        }
    }

    private fun setEvents() {
        // Defining memoryCards
        // val memoryCards = randomizedImages.map{ NapsakCard(it) }
        memoryGame = MemoryGame(boardSize)

        // Recycler view adapter: The Adapter provides access to the data items.
        adapter = NapsakBoardAdapter(requireContext(),boardSize,memoryGame.events, object: NapsakBoardAdapter.CardClickListener{
            override fun onCardClicked(position: Int) {
                Log.i("Event List F.", "Card clicked $position")
                updateWithFlip(position)
            }

            private fun updateWithFlip(position: Int) {
                if(memoryGame.eventListDB.isEmpty()){
                    // if the card is already flipped open detail fragment
                    if(memoryGame.events[position].isFaceUp){
                        Log.i("Event List F.", "Event Detail Fragment will be shown")
                        val eventActivityIntent = Intent(requireContext(),ActivitySelect::class.java)
                        eventActivityIntent.putExtra("activity",memoryGame.events[position])
                        startActivity(eventActivityIntent)
                    }else{
                        memoryGame.flipCard(position)
                        adapter.notifyDataSetChanged()
                    }
                }else{
                    // if the card is already flipped open detail fragment
                    if(memoryGame.eventListDB[position].isFaceUp){
                        Log.i("Event List F.", "Event Detail Fragment will be shown")
                        val eventActivityIntent = Intent(requireContext(),ActivitySelect::class.java)
                        eventActivityIntent.putExtra("activity",memoryGame.eventListDB[position])
                        startActivity(eventActivityIntent)
                    }else{
                        memoryGame.flipCard(position)
                        adapter.notifyDataSetChanged()
                    }
                }


            }

        })

        // Pass the adapter and layout manager
        rvBoard.adapter = adapter
        mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        mEventViewModel.readAllData.observe(viewLifecycleOwner, Observer{ event_list ->
            Log.i("List: ",event_list.toString())
            adapter.setData(event_list)
            memoryGame.setEventList(event_list)
        })
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager = GridLayoutManager(requireContext(),2) // Column numbers
    }

    private val newEventclickListener: View.OnClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.cvNewEvent -> {
                // Inform the user
                Toast.makeText(requireContext(),"What's your event?",Toast.LENGTH_LONG).show()
                // update the events
                val newEventIntent = Intent(requireContext(),NewEventActivity::class.java)
                startActivity(newEventIntent)
            }
        }
    }



}
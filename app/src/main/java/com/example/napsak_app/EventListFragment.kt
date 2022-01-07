package com.example.napsak_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.napsak_app.databinding.FragmentActivityDetailsBinding
import com.example.napsak_app.databinding.FragmentEventListBinding
import com.example.napsak_app.models.*
import com.example.napsak_app.services.MyEventHandler
import com.example.napsak_app.services.UserAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Error
import kotlin.math.pow


class EventListFragment : Fragment() {

    companion object {
        private var TAG = "EVENT LIST FRAGMENT";
    }

    private lateinit var listBinding: FragmentEventListBinding

    private lateinit var memoryGame: MemoryGame
    private lateinit var adapter: NapsakBoardAdapter
    private lateinit var user: User;

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

        try {
            userWork();
            getUser();
        }catch (error:Error){
            Log.i("ERROR GET USER:",error.toString());
        }

        // Defining memoryCards
        // val memoryCards = randomizedImages.map{ NapsakCard(it) }
        memoryGame = MemoryGame(boardSize)


        mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        mEventViewModel.readAllData.observe(viewLifecycleOwner, Observer{ event_list ->

            memoryGame.setEventList(event_list.shuffled())
            memoryGame.eventListDB.forEach {
                it.isFaceUp = false;
            }
            // Recycler view adapter: The Adapter provides access to the data items.
            Log.i("Event DB size: ",memoryGame.eventListDB.size.toString());

            adapter = NapsakBoardAdapter(requireContext(),boardSize,memoryGame.events, object: NapsakBoardAdapter.CardClickListener{
                override fun onCardClicked(position: Int) {
                    Log.i("Event List F.", "Card clicked $position")
                    updateWithFlip(position)
                }
                private fun updateWithFlip(position: Int) {
                    Log.i("DB Length: ",memoryGame.eventListDB.size.toString());
                    if(memoryGame.eventListDB.isEmpty()){
                        // if the card is already flipped open detail fragment
                        if(memoryGame.events[position].isFaceUp){
                            Log.i("DEFAULT", "Event Detail Fragment will be shown")
                            val eventActivityIntent = Intent(requireContext(),ActivitySelect::class.java)
                            eventActivityIntent.putExtra("activity",memoryGame.eventListDB[position])
                            startActivity(eventActivityIntent)
                        }else{
                            memoryGame.flipCard(position)
                            adapter.notifyDataSetChanged()
                        }
                    }else{
                        // if the card is already flipped open detail fragment
                        if(memoryGame.eventListDB[position].isFaceUp){
                            Log.i("DATABASE", "Event Detail Fragment will be shown")
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
            adapter.setData(memoryGame.eventListDB);
            rvBoard.adapter = adapter
            rvBoard.setHasFixedSize(true)
            rvBoard.layoutManager = GridLayoutManager(requireContext(),2) // Column numbers
        })
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

    // to change activity count and grid
    private fun changeActivityGrid(){
        // get the board size variable
        val boardSizeView: View = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_board_size,null)
        val radioBtnGroup = boardSizeView.findViewById<RadioGroup>(R.id.rgEventGrid)

        // set default check
        when(boardSize){
            BoardSize.EASY -> radioBtnGroup.check(R.id.rbMinimal)
            BoardSize.MEDIUM -> radioBtnGroup.check(R.id.rbMiddle)
            BoardSize.HARD -> radioBtnGroup.check(R.id.rbMany)
        }

        // show alert dialog to interact with the user
        showAlertDialog("More Events?","No Thanks","Sure!",boardSizeView,View.OnClickListener {
            boardSize = when (radioBtnGroup.checkedRadioButtonId) {
                R.id.rbMinimal -> BoardSize.EASY
                R.id.rbMiddle -> BoardSize.MEDIUM
                R.id.rbMany -> BoardSize.HARD
                else -> {
                    BoardSize.MEDIUM
                }
            }
            Log.i("List Fragment: ","New Board Size: $boardSize")
            setEvents()
        })

    }

    // Create an alert dialog function
    private fun showAlertDialog(title: String, cancelStr: String,acceptStr: String,view: View?, positiveClickListener: View.OnClickListener) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setView(view)
            .setNegativeButton(cancelStr, null)
            .setPositiveButton(acceptStr) { _, _ ->
                positiveClickListener.onClick(null)
            }.show()
    }

    private fun refreshEvents(){
        Log.i(TAG,"Events Refresh!")

        memoryGame.setEventList(memoryGame.eventListDB.shuffled())
        memoryGame.eventListDB.forEach {
            it.isFaceUp = false;
        }
        adapter.setData(memoryGame.eventListDB);
    }

    private fun getUser() {
        UserAPI.retrofitService.getUser().enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>?, response: Response<User>?){
                if (response != null) {
                    Log.i("USER: ", response.body().toString())
                    user = response.body()!!
                    val events = eventOrderAlgorithm()
                    memoryGame.setEventList(events);
                    memoryGame.eventListDB.forEach {
                        it.isFaceUp = false;
                    }
                    adapter.setData(memoryGame.eventListDB);
                }
            }

            override fun onFailure(call: Call<User>?, t: Throwable?) {
                if (t != null) {
                    t.message?.let { Log.i("USER ERROR: ", it) }
                }
            }

        })
    }

    private fun eventOrderAlgorithm(): List<Event>{

        val eventPoints = mutableListOf<Double>();
        val eventOrdered = mutableListOf<Event>()

        memoryGame.eventListDB.forEach {
            var sse = 0.0;
            if(user != null){
                sse = (it.timePoint - user.timeP).toDouble().pow(2.0)
                +(it.physicalPoint - user.physicalP).toDouble().pow(2.0)
                +(it.socialPoint - user.socialP).toDouble().pow(2.0)
                +(it.entertainmentPoint - user.entertainmentP).toDouble().pow(2.0);
            }
            val random = Math.random() * 50;
            val eventPoint = sse + random;

            eventPoints.add(eventPoint);
        }

        Log.i("POINTS: ", eventPoints.toString())
        val sortedEventList = eventPoints.sortedDescending();
        Log.i("SORT: ", sortedEventList.toString())

        sortedEventList.forEach {
            val index = eventPoints.indexOf(it)
            Log.i("Index: ",index.toString());
            eventOrdered.add(memoryGame.eventListDB[index]);
        }

        return eventOrdered

    }

    private fun userWork(){

        val userRequest : WorkRequest = OneTimeWorkRequestBuilder<MyEventHandler>().build();

        WorkManager.getInstance(requireContext()).enqueue(userRequest)
    }

}
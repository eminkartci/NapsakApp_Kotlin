
/*
Project: Napsak
Authors:
- Emin Kartci
- Metin Arda Koker
Date: 2 November 2021
GitHub: https://github.com/eminkartci/NapsakApp_Kotlin
 */
package com.example.napsak_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.napsak_app.databinding.ActivityMainBinding
import com.example.napsak_app.models.*
import com.example.napsak_app.utils.DEFAULT_ICONS
import java.lang.Error

// MainActivitiy is the first activated activitiy
class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    // lateinit: those variables will be initialized later because the instances are not created ye
    private lateinit var memoryGame: MemoryGame
    private lateinit var adapter: NapsakBoardAdapter
    private lateinit var mainContext: Context

    private var boardSize: BoardSize = BoardSize.EASY

    // onCreate is a default function
    override fun onCreate(savedInstanceState: Bundle?) {
        // call super constructor
        super.onCreate(savedInstanceState)

        // DATA BINDING
        val mainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainContext = this

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.napsak_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.im_personal_test -> {
                // Go to the test activity
                val personalTestIntent = Intent(mainContext,PersonalTestActivity::class.java)
                startActivity(personalTestIntent)
            }

            R.id.im_change_size  -> {
                changeActivityGrid()
            }
        }

        return super.onOptionsItemSelected(item)
    }



    private val newEventclickListener: View.OnClickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.cvNewEvent -> {
                // Inform the user
                Toast.makeText(mainContext,"What's your event?",Toast.LENGTH_LONG).show()
                // update the events
                val newEventIntent = Intent(mainContext,NewEventActivity::class.java)
                startActivity(newEventIntent)
            }
        }
    }

    // to change activity count and grid
    private fun changeActivityGrid(){
        // get the board size variable
        val boardSizeView: View = LayoutInflater.from(mainContext).inflate(R.layout.dialog_board_size,null)
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
            Log.i(TAG,"New Board Size: $boardSize")
            // set fragment's rv board level
            
        })


    }

    // Create an alert dialog function
    private fun showAlertDialog(title: String, cancelStr: String,acceptStr: String,view: View?, positiveClickListener: View.OnClickListener) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setView(view)
            .setNegativeButton(cancelStr, null)
            .setPositiveButton(acceptStr) { _, _ ->
                positiveClickListener.onClick(null)
            }.show()
    }
}



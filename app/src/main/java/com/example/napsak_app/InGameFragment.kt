package com.example.napsak_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainer

class InGameFragment : Fragment() {

    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{

        val view = inflater.inflate(R.layout.fragment_in_game,container, false)

        val gameOverListener: (View) -> Unit = {
            view.findNavController().navigate(R.id.action_inGame_to_gameOverFragment)
        }

        view.findViewById<View>(R.id.checkBox).setOnClickListener(gameOverListener)
        view.findViewById<View>(R.id.checkBox2).setOnClickListener(gameOverListener)

        view.findViewById<View>(R.id.checkBox3).setOnClickListener {
            view.findNavController().navigate(R.id.action_inGameFragment_to_gameWinFragment)
        }
        return view

    }

}
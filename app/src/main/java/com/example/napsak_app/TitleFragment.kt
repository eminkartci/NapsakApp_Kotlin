package com.example.napsak_app

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class TitleFragment : Fragment(R.layout.fragment_title) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstance: Bundle?
    ): View? {

        Log.i("TitleFragment","OnCreateView called")
        val view = inflater.inflate(R.layout.fragment_title, container,false)
        view.findViewById<Button>(R.id.play_btn).navigate(R.id.action_titleFragment_to_inGameFragment){
    }
    view.findViewById<Button>(R.id.leaderboard_btn).setOnclickListener {
        Navigation.findNavController(view).navigate(R.id.action_titleFragment_to_leaderBoardFragment)
    }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("TitleFragment","onViewCreated called")

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("TitleFragment","onAttach called")

    }

    override fun onStart() {
        super.onStart()
        Log.i("TitleFragment","onStart called")

    }

    override fun onResume() {
        super.onResume()
        Log.i("TitleFragment","onResume called")

    }

    override fun onPause() {
        super.onPause()
        Log.i("TitleFragment","onPause called")

    }

    override fun onStop() {
        super.onStop()
        Log.i("TitleFragment","onStop called")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("TitleFragment","onDestroyView called")

    }

    override fun onDetach() {
        super.onDetach()
        Log.i("TitleFragment","onDetach called")

    }

}

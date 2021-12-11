package com.example.napsak_app.utils

import android.app.Person
import com.example.napsak_app.PersonalQuestion
import com.example.napsak_app.R

// The Data of the card front page with Icons it will be changed
val DEFAULT_ICONS = listOf(
    R.drawable.reading,
    R.drawable.chatting,
    R.drawable.walking,
    R.drawable.cinema,
    R.drawable.music,
    R.drawable.cooking,
    R.drawable.drink_water,
    R.drawable.language,
    R.drawable.podcast,
    R.drawable.youtube
)

object Constants{

    // get questions
    fun getPersonalQuestions(): ArrayList<PersonalQuestion>{
        val personalQuestionList = ArrayList<PersonalQuestion>()

        val timeQuestion = PersonalQuestion(1,"Do you have time?",R.drawable.music,-1)
        val socialQuestion = PersonalQuestion(1,"Do you want to socialize?",R.drawable.chatting,-1)
        val selfImprovementQuestion = PersonalQuestion(1,"Do you want to improve yourself?",R.drawable.language,-1)
        val physicalQuestion = PersonalQuestion(1,"Do you want to do physical activity?",R.drawable.walking,-1)

        personalQuestionList.add(timeQuestion)
        personalQuestionList.add(socialQuestion)
        personalQuestionList.add(selfImprovementQuestion)
        personalQuestionList.add(physicalQuestion)

        return personalQuestionList
    }
}
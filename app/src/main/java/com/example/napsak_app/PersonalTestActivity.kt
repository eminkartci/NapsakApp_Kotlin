package com.example.napsak_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.napsak_app.databinding.ActivityMainBinding
import com.example.napsak_app.databinding.ActivityPersonalTestBinding
import com.example.napsak_app.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PersonalTestActivity : AppCompatActivity() {

    private var currentQuestionIndex : Int = -1
    private lateinit var personalQuestions: ArrayList<PersonalQuestion>

    private lateinit var personalTestBinding: ActivityPersonalTestBinding
    private lateinit var personalTestContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        personalTestContext = this
        personalTestBinding= DataBindingUtil.setContentView(this,R.layout.activity_personal_test)
        //setContentView(R.layout.activity_personal_test)

        // home icon show
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        // update the title
        supportActionBar?.title = "Take Personal Test"

        // get questions
        personalQuestions = Constants.getPersonalQuestions()
        
        // get the first question
        nextQuestion()
        addListenerstoOptions()
        // add click listeneter
        personalTestBinding.btnSubmit.setOnClickListener {
            if (isAnswered(personalQuestions.get(currentQuestionIndex))){
                saveFireStore(currentQuestionIndex,personalQuestions.get(currentQuestionIndex).answer)
                nextQuestion()
            }else{
                Toast.makeText(this,"Please answer the question.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home){
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun nextQuestion(){
        currentQuestionIndex += 1
        if(isQuestionsFinished()){
            Log.i("QUESTIONS: ","Test is finished")
            val mainActivityIntent = Intent(personalTestContext,MainActivity::class.java)
            var choicesArrayList = ArrayList<Int>()
            choicesArrayList.add(personalQuestions.get(0).answer)
            choicesArrayList.add(personalQuestions.get(1).answer)
            choicesArrayList.add(personalQuestions.get(2).answer)
            choicesArrayList.add(personalQuestions.get(3).answer)


            mainActivityIntent.putExtra("Personal_Choices",choicesArrayList)
            startActivity(mainActivityIntent)
            finish()
            return;
        }
        Log.i("Question Size: ", "${personalQuestions.size}")
        Log.i("Question Index: ",currentQuestionIndex.toString())
        personalTestBinding.tvPqQuestion.text = personalQuestions[currentQuestionIndex].question
        setSelectedBackground(-1)

    }

    private fun saveFireStore(questionIndex:Int,answer:Int) {

        // create FireStore instance
        val db = FirebaseFirestore.getInstance()
        val question : MutableMap<String,Int> = HashMap()

        question["Question: $questionIndex"] = answer;

        db.collection("Questions")
            .add(question)
            .addOnSuccessListener {
                Toast.makeText(this,"Question Saved",Toast.LENGTH_SHORT);
                Log.i("Firebase","The Question is saved!!")
            }
            .addOnFailureListener {
                Log.i("Firebase","The Question couldn't saved")
            }

    }


    private fun isQuestionsFinished(): Boolean {
        return personalQuestions.size -1 == currentQuestionIndex
    }

    private fun isAnswered(pq : PersonalQuestion): Boolean {
        return pq.answer != -1
    }


    private fun addListenerstoOptions(){

        personalTestBinding.tvOption1.setOnClickListener{
            personalQuestions.get(currentQuestionIndex).answer = 1
            setSelectedBackground(1)
        }

        personalTestBinding.tvOption2.setOnClickListener{
            personalQuestions.get(currentQuestionIndex).answer = 2
            setSelectedBackground(2)
        }

        personalTestBinding.tvOption3.setOnClickListener{
            personalQuestions.get(currentQuestionIndex).answer = 3
            setSelectedBackground(3)
        }

        personalTestBinding.tvOption4.setOnClickListener{
            personalQuestions.get(currentQuestionIndex).answer = 4
            setSelectedBackground(4)
        }

        personalTestBinding.tvOption5.setOnClickListener{
            personalQuestions.get(currentQuestionIndex).answer = 5
            setSelectedBackground(5)
        }


    }

    private fun setSelectedBackground(optionIndex: Int){
        personalTestBinding.tvOption1.background = ContextCompat.getDrawable(personalTestContext,R.drawable.default_option_border_bg)
        personalTestBinding.tvOption2.background = ContextCompat.getDrawable(personalTestContext,R.drawable.default_option_border_bg)
        personalTestBinding.tvOption3.background = ContextCompat.getDrawable(personalTestContext,R.drawable.default_option_border_bg)
        personalTestBinding.tvOption4.background = ContextCompat.getDrawable(personalTestContext,R.drawable.default_option_border_bg)
        personalTestBinding.tvOption5.background = ContextCompat.getDrawable(personalTestContext,R.drawable.default_option_border_bg)

        when(optionIndex){
            1-> personalTestBinding.tvOption1.background = ContextCompat.getDrawable(personalTestContext,R.drawable.selected_option_border_bg)
            2-> personalTestBinding.tvOption2.background = ContextCompat.getDrawable(personalTestContext,R.drawable.selected_option_border_bg)
            3-> personalTestBinding.tvOption3.background = ContextCompat.getDrawable(personalTestContext,R.drawable.selected_option_border_bg)
            4-> personalTestBinding.tvOption4.background = ContextCompat.getDrawable(personalTestContext,R.drawable.selected_option_border_bg)
            5-> personalTestBinding.tvOption5.background = ContextCompat.getDrawable(personalTestContext,R.drawable.selected_option_border_bg)
        }
    }


}
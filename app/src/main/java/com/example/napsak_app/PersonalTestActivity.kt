package com.example.napsak_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.napsak_app.databinding.ActivityMainBinding
import com.example.napsak_app.databinding.ActivityPersonalTestBinding
import com.example.napsak_app.utils.Constants

class PersonalTestActivity : AppCompatActivity() {

    private var currentQuestionIndex : Int = 0
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

        if(isQuestionsFinished()){
            val mainActivityIntent = Intent(personalTestContext,MainActivity::class.java)
            mainActivityIntent.putExtra("Personal_Choices",personalQuestions)
            startActivity(mainActivityIntent)
            finish()
            return;
        }
        // Log.i("Question Size: ", "${personalQuestions.size}")
        personalTestBinding.tvPqQuestion.text = personalQuestions.get(currentQuestionIndex).question

        currentQuestionIndex += 1
    }

    private fun isQuestionsFinished(): Boolean {
        return personalQuestions.size == currentQuestionIndex -1
    }

    private fun isAnswered(pq : PersonalQuestion): Boolean {
        return pq.answer != -1
    }

    private fun addListenerstoOptions(){

        personalTestBinding.tvOption1.setOnClickListener{
            personalQuestions.get(currentQuestionIndex).answer = 1
            nextQuestion()
        }

        personalTestBinding.tvOption2.setOnClickListener{
            personalQuestions.get(currentQuestionIndex).answer = 2
            nextQuestion()
        }

        personalTestBinding.tvOption3.setOnClickListener{
            personalQuestions.get(currentQuestionIndex).answer = 3
            nextQuestion()
        }

        personalTestBinding.tvOption4.setOnClickListener{
            personalQuestions.get(currentQuestionIndex).answer = 4
            nextQuestion()
        }

        personalTestBinding.tvOption5.setOnClickListener{
            personalQuestions.get(currentQuestionIndex).answer = 5
            nextQuestion()
        }
    }



}
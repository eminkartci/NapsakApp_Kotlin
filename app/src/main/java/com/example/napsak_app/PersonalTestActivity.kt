package com.example.napsak_app

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        // Log.i("Question Size: ", "${personalQuestions.size}")
        personalTestBinding.tvPqQuestion.text = personalQuestions.get(currentQuestionIndex).question

        currentQuestionIndex += 1
    }

    private fun isAnswered(pq : PersonalQuestion): Boolean {
        return pq.answer != -1
    }


}
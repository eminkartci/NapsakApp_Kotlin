package com.example.napsak_app

class Question (var question:String, var answers: List<String>, var correctAnswer: String) {

    val allQuestionList:List<Question> =
        ListOf(
            Question("How many miles are in a marathon?",ListOf("13","42","26.2"),"26.2"),
            Question("Which one is not a prime number?",ListOf("39","23","47"),"39"),
            Question("Which following company is not building a commercial space station?",ListOf("Blue Origin","Axiom Space","Space X"),"Space X")
        )

}
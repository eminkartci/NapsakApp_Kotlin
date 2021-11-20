package com.example.napsak_app.models

import java.io.Serializable

data class User(val username:String, val age: String, val hobbies: List<String>):Serializable
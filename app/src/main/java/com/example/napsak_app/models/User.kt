package  com.example.napsak_app.models

import java.io.Serializable


data class User(
    val username:String,
    val age: Int,
    val hobbies: List<String>,
    val matching_percenrage: String,
    val activity_count: String):Serializable
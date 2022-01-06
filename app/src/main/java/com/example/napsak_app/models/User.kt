package  com.example.napsak_app.models

import com.squareup.moshi.Json
import java.io.Serializable


data class User(
    @Json(name = "username") val username:String,
    @Json(name = "age")val age: Int,
    @Json(name = "timeP")val timeP: Int,
    @Json(name = "socialP")val socialP: Int,
    @Json(name = "physicalP")val physicalP: Int,
    @Json(name = "entertainmentP")val entertainmentP: Int):Serializable
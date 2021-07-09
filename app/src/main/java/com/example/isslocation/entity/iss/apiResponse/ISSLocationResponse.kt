package com.example.isslocation.entity.iss.apiResponse

import com.example.isslocation.entity.iss.ISS
import com.google.gson.annotations.SerializedName

class ISSLocationResponse (
        @SerializedName("iss_position")
    var issPosition : ISS,

        @SerializedName("timestamp")
    var timestamp : Int?,

        @SerializedName("message")
    var message : String?
)
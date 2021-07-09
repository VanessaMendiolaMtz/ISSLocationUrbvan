package com.example.isslocation.entity

import com.google.gson.annotations.SerializedName

class ISS (
    @SerializedName("latitude")
    var issLatitude: String?  = "",

    @SerializedName("longitude")
    var issLongitude: String? = ""
)
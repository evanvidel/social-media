package com.axweb.socialmedia.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    var refresh: String,
    @SerializedName("access") var token : String
)

package com.example.qradmin11.entity

import com.google.firebase.database.Exclude

data class User (
    @get:Exclude
    var login:String?=null,
    var password:String?=null,
    var status: String = ""
)
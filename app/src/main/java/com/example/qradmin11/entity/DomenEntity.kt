package com.example.qradmin11.entity

import com.google.firebase.database.Exclude

data class DomenEntity (
    @get:Exclude
    var id:String?=null,
    var domenName:String?=null
)
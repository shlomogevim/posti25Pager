package com.example.posti25pager.models

import com.google.firebase.Timestamp

data class Article (
    var aricleNum:Int=0,
     var aricleTitle:String="",
     var aricleText:String="",
    var articleBackground:String="",
    var articleTextColor:String="",
    var articleTextSize:Int=14,
    var articleFontFamily:Int=0,
    var timestamp: Timestamp?=null

)

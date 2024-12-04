package com.example.smallbusinessplan.Extensions

import android.app.Activity
import android.view.View
import android.widget.Toast


fun View.gone(){
    visibility=View.GONE
}
fun View.visible(){
    visibility=View.VISIBLE
}
fun View.inVisible(){
    visibility=View.INVISIBLE
}
fun Activity.showToast(msg:String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
fun Activity.showLongToast(msg:String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
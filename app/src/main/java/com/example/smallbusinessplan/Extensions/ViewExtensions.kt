package com.example.smallbusinessplan.Extensions

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlin.reflect.KClass


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
fun Fragment.FragmentShowToast(msg: String) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}
fun Activity.showLongToast(msg:String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
fun Activity.ActivityIntent(destination : KClass<out Activity>){
    val i = Intent(this, destination.java)
    startActivity(i)
}
fun Fragment.FragmentIntent(destination : KClass<out Activity>){
    val i = Intent(requireContext(), destination.java)
    startActivity(i)
}
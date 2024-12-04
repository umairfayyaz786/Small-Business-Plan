package com.example.smallbusinessplan

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replaceFragmentSafely(
    fragment: Fragment,
    @IdRes containerView: Int = R.id.spinnerframe
) {
    val fragmentManager = supportFragmentManager
    val fragmentTransition = fragmentManager.beginTransaction()
    fragmentTransition.replace(containerView, fragment)
    fragmentTransition.commit()
}

fun Fragment.replaceFragmentSafely(
    fragment: Fragment,
    @IdRes containerView: Int = R.id.spinnerframe
) {
    val fragmentManager = activity?.supportFragmentManager
    val fragmentTransition = fragmentManager?.beginTransaction()
    fragmentTransition?.replace(containerView, fragment)
    fragmentTransition?.commit()
}
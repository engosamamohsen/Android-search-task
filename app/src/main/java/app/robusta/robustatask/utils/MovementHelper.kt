package app.robusta.robustatask.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import app.robusta.robustatask.R
object MovementHelper {
    //---------Fragments----------//
    private const val CONTAINER_ID = R.id.fl_home_container
    fun replaceFragment(context: Context, fragment: Fragment,tag: String = "") {
        val fragmentManager = (context as FragmentActivity?)?.supportFragmentManager
        val fragmentTransaction = if(tag == "")
            fragmentManager?.beginTransaction()?.replace(CONTAINER_ID, fragment)
        else
            fragmentManager?.beginTransaction()?.replace(CONTAINER_ID, fragment, tag)

        fragmentTransaction?.commit()
    }
}
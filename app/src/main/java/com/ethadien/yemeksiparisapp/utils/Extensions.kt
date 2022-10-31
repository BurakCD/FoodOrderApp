package com.ethadien.yemeksiparisapp.utils

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar

fun showSnackbar(view: View, text: Int) {
    Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
}

fun Navigation.gate(view : View, nav_id:Int){
    findNavController(view).navigate(nav_id)
}

fun Navigation.gate(view : View, nav_id: NavDirections){
    findNavController(view).navigate(nav_id)
}
package com.android.movieapp.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

fun ProgressBar.isShowProgressBar(visible: Boolean) {
    if (visible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun showSnackBar(msg: String, activity: Activity) {
    activity.view()?.let {
        Snackbar.make(it, msg, Snackbar.LENGTH_LONG)
            .show()
    }
}

fun Activity.view(): View? =
    findViewById(android.R.id.content)

fun RecyclerView.setDividerItemDecoration(context: Context, orientation: Int, resId: Int) {
    val divider = DividerItemDecoration(context, orientation)
    AppCompatResources.getDrawable(context, resId)?.let {
        divider.setDrawable(it)
    }
    this.addItemDecoration(divider)
}
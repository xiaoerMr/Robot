package com.sai.jetpack.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class ToastUtils {
    companion object {
        fun show(view: View, msg: String) {
            Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
        }

        fun show(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }
}
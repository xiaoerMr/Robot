package com.sai.robot.addview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class DecorAdapter  {
    abstract fun onCreateDecorView(inflater: LayoutInflater):View
    abstract fun getContentParent(decorView: View):ViewGroup

}
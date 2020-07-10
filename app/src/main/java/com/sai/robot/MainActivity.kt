package com.sai.robot

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sai.robot.utils.TestUtlis
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vRobot.setOnClickListener { KeDaActivity.actionStart(this) }
    }

//
//    @Composale
//    fun Greetig(){
//        val (shape,setShape) = state<Shape> { CircleShape }
//        Image(asset = imageResource(R.drawable.androidstudio), contentScale = ContentScale.Crop )
//    }
}
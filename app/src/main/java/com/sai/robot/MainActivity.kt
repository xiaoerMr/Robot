package com.sai.robot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sai.robot.addview.AddActivity
import com.sai.robot.aidl.AidlActivity
import com.sai.robot.corouties.CoroutinesActivity
import com.sai.robot.dome.PagingActivity
import com.sai.robot.socket.OkSocketActivity
import com.sai.robot.socket.SocketActivity
import com.sai.robot.svg.SVGActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vRobot.setOnClickListener { KeDaActivity.actionStart(this) }
        vCoroutines.setOnClickListener { CoroutinesActivity.actionStart(this) }
        vSVG.setOnClickListener { SVGActivity.actionStart(this) }
        vAdd.setOnClickListener { AddActivity.actionStart(this) }
        vDome.setOnClickListener { PagingActivity.actionStart(this) }
        vPage3.setOnClickListener { }//PageActivity.actionStart(this) }
//        vSocket.setOnClickListener { OkSocketActivity.actionStart(this) }
        vSocket.setOnClickListener { SocketActivity.actionStart(this) }
        vAidl.setOnClickListener { AidlActivity.actionStart(this) }
//        vDome.isSelected = true
    }


    //-----知识点-----
    /**
     * 伴生对象
     * 类初始化顺序： 伴生对象 companion object  >  init()  > 次级构造方法 constructor
     * 类似于静态方法
     */

    // 声明式UI编程 框架： Jetpack Composale  android studio 4.1以上
//    @Composale
//    fun Greetig(){
//        val (shape,setShape) = state<Shape> { CircleShape }
//        Image(asset = imageResource(R.drawable.androidstudio), contentScale = ContentScale.Crop )
//    }
}
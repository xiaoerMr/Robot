package com.sai.robot.svg

import android.content.Intent
import android.graphics.drawable.Animatable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sai.robot.R
import kotlinx.android.synthetic.main.activity_s_v_g.*

class SVGActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_s_v_g)
        supportActionBar?.title = "矢量图动画"

        val animatable = vSvgAnimator.drawable as Animatable
        val line = vLine.drawable as Animatable
        val menu = vMenu.drawable as Animatable
        val loading = vLoading.drawable as Animatable
        animatable.start()
        line.start()
        menu.start()
        loading.start()

    }

    companion object {
        fun  actionStart(activity: AppCompatActivity){
            activity.startActivity(Intent(activity, SVGActivity::class.java))

        }
    }
}
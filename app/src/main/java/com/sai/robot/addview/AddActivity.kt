package com.sai.robot.addview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sai.robot.R
import com.sai.robot.addview.adapter.ToolbarAdapter
import com.sai.robot.addview.adapter.ViewType

class AddActivity : AppCompatActivity() {

    companion object {
        fun actionStart(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, AddActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val toolbarUtils = ToolbarUtils(this)
        toolbarUtils.register(ViewType.TITLE, ToolbarAdapter("回纥"))
        toolbarUtils.setDecorHeader(ViewType.TITLE)
    }


}
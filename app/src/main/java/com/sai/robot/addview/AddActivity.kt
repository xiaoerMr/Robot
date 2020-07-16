package com.sai.robot.addview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sai.robot.R
import com.sai.robot.addview.adapter.ToolbarAdapter
import com.sai.robot.addview.adapter.ViewType
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.view_show_text.view.*

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

        name.title.text ="姓名"
        name.txt.text ="雕细哦儿"

        phone.title.text ="电话"
        phone.txt.text ="1583923423489237"

        address.title.text = "地址"
        address.txt.text = "成华大道——成华大道-成华大道-成华大道-成华大道-成华大道"
    }


}
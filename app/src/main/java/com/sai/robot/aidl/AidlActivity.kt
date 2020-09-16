package com.sai.robot.aidl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sai.robot.R
import kotlinx.android.synthetic.main.activity_aidl.*

class AidlActivity : AppCompatActivity() {
    companion object {
        fun actionStart(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, AidlActivity::class.java))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aidl)

        doStartApp.setOnClickListener { doStartApp() }
    }

    private fun doStartApp() {

        if (checkPackInfo()) {
            val intent =
                packageManager.getLaunchIntentForPackage("com.sai.founder")
            if (null ==intent) {
                Log.e("TAG", "doStartDisplay: intent == null" )
            }else{
                startActivity(intent)
                Log.e("TAG", "start app" )
            }
        }else{
            Log.e("TAG", "checkPackInfo == false" )
        }
    }

    private fun checkPackInfo() :Boolean{
        try {
            val packageInfo = packageManager.getPackageInfo("com.sai.founder", 0)
            return packageInfo != null
        }catch (e:Exception){
            return false
        }
    }
}
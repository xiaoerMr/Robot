package com.sai.robot.utils

import android.util.Log


class TestUtlis  {

    companion object {
        init {
            Log.d("--","--companion object-1-")
            Thread.sleep(100)
        }
    }

    init {
        Log.d("--","--init-2-")
        Thread.sleep(100)
    }

    constructor() {
        Log.d("--","--constructor-3-")
    }

}
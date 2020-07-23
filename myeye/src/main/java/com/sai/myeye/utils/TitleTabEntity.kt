package com.sai.myeye.utils

import com.flyco.tablayout.listener.CustomTabEntity

class TitleTabEntity constructor(var title: String, var icon: Int = 0, var unIcon: Int = 0) : CustomTabEntity {

    override fun getTabUnselectedIcon(): Int = unIcon

    override fun getTabSelectedIcon(): Int = icon

    override fun getTabTitle(): String = title
}
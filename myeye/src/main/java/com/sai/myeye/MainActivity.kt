package com.sai.myeye

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.sai.myeye.commend.CommendFragment
import com.sai.myeye.daily.DailyFragment
import com.sai.myeye.discovery.DiscoveryFragment
import com.sai.myeye.utils.TitleTabEntity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTabLayout()

        initViewPager()

    }

    private fun initViewPager() {

        val fragments : Array<Fragment> = arrayOf(DiscoveryFragment.newInstance(), CommendFragment.newInstance(), DailyFragment.newInstance())

        vViewPager.registerOnPageChangeCallback(PageCallback())

        vViewPager.offscreenPageLimit = 1

        val adapterPager = AdapterPager(this)
        adapterPager.addFragments(fragments)

        vViewPager.adapter = adapterPager
    }

    private fun initTabLayout() {
        val titles = ArrayList<CustomTabEntity>().apply {
            add(TitleTabEntity("发现"))
            add(TitleTabEntity("推荐"))
            add(TitleTabEntity("日报"))
        }

        vTabLayout.setTabData(titles)
        vTabLayout.setOnTabSelectListener(object :OnTabSelectListener{
            override fun onTabSelect(position: Int) {
                vViewPager.currentItem = position
            }

            override fun onTabReselect(position: Int) {
//                vViewPager.currentItem = position
            }
        })


    }

    inner class PageCallback: ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            vTabLayout.currentTab=position
        }
    }
    inner class AdapterPager(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

        private val fragments = mutableListOf<Fragment>()
        fun addFragments(fragment: Array<Fragment>){
            fragments.addAll(fragment)
        }

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}

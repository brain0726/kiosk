package com.example.kiosk

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kiosk.ViewPager.FirstFragment
import com.example.kiosk.ViewPager.SecondFragment
import com.example.kiosk.ViewPager.ThirdFragment

open class ViewPagerAdapter(private val activity: MainActivity) : FragmentStateAdapter(activity){

private val dataList: MutableList<String> = mutableListOf()

    var totalmoney:Int=0
    private val fragments = listOf(
        FirstFragment(),
        SecondFragment(),
        ThirdFragment()
    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment=fragments[position]


        return fragment
    }



}
package com.example.kiosk

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.kiosk.databinding.ActivityMainBinding
import com.example.kiosk.databinding.FragmentFirstMenuBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(){

    private var _binding: ActivityMainBinding? = null
    private val binding get() =_binding
    var money:Int=0
        set(value) {
            field = value
            Log.d("MyTag", "더하기전 money: $money" )
            binding?.moneysum?.text = value.toString()
        }
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)


            _binding= ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding!!.root)

            binding!!.viewpager2.adapter=ViewPagerAdapter(this)
            val tabTitles = arrayOf("coffee", "tea", "bread")

            TabLayoutMediator(binding!!.tab, binding!!.viewpager2) { tab, position ->
                tab.text = tabTitles[position]
            }.attach()

    }


}
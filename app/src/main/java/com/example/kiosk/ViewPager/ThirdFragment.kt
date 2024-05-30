package com.example.kiosk.ViewPager

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.kiosk.Dialog.Bread.BreadDialogFragment
import com.example.kiosk.R
import com.example.kiosk.databinding.FragmentThirdMenuBinding
import com.example.kiosk.model.CafeData


class ThirdFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding= FragmentThirdMenuBinding.inflate(layoutInflater)

        var fAdapter=MyGridAdapter()
        binding.bread.adapter=fAdapter
        return binding.root
    }
    inner class MyGridAdapter: BaseAdapter(){

        var menuID= arrayOf(
            R.layout.activity_menu_croissant, R.layout.activity_menu_croquemonsieur,
            R.layout.activity_menu_plainbagel, R.layout.activity_menu_pretzels
        )
        val type=3//coffee
        val coffeeids= arrayOf(21,22,23,24)
        val dialogid= arrayOf(1,1,1,1)
        val names= arrayOf("아메리카노","카페라떼","바닐라라떼","카푸치노")
        val srcs = arrayOf(
            R.drawable.americano,
            R.drawable.cafelatte,
            R.drawable.vanillalatte,
            R.drawable.cafemocha
        )
        //count=1
        val prices=arrayOf(
            3200,4200,4500,4500
        )
        val payoptionsize= 9

        override fun getCount(): Int {
            return menuID.size
        }

        override fun getItem(position: Int): Any {
            return 0
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var menuview= p1
            if (menuview == null) {
                menuview = LayoutInflater.from(p2?.context).inflate(menuID[p0], p2, false)
            }
            menuview!!.setOnClickListener{
                val dialog = BreadDialogFragment()
                val args = Bundle()
                dialog.arguments = args
                dialog.show(parentFragmentManager, "CustomDialog")
            }
            return menuview!!
        }
    }
}
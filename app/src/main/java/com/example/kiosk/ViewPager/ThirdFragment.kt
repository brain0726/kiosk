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
        val type=3//bread
        val coffeeids= arrayOf(21,22,23,24)
        val dialogid= arrayOf(1,1,1,1)
        val names= arrayOf("크루아상","크로크무슈","플레인베이글","프레즐")
        val srcs = arrayOf(
            R.drawable.croissant,
            R.drawable.croquemonsieur,
            R.drawable.plainbagel,
            R.drawable.pretzels
        )
        //count=1
        val prices=arrayOf(
            2900,4700,2500,2700
        )
        val payoptionsize= 1

        val dialogfreeiceids= arrayOf(0,0,0,0)
        val dialogfreehotids= arrayOf(0,0,0,0)


        val freehotoptionsize= arrayOf(1,1,1,1)
        val freeiceoptionsize= arrayOf(1,1,1,1)

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

                var payoption = Array(payoptionsize) { 0 }
                var freeiceoption = Array(freeiceoptionsize[p0]) { 0 }

                var freehotoption = Array(freehotoptionsize[p0]) { 0 }
                val cafeData= CafeData(type,coffeeids[p0],dialogid[p0],
                    dialogfreeiceids[p0],dialogfreehotids[p0],names[p0],
                    srcs[p0],1,prices[p0],0,0,payoption,
                    0,0,freehotoption,freeiceoption)

                args.putParcelable("CafeData", cafeData)

                dialog.arguments = args
                dialog.show(parentFragmentManager, "CustomDialog")
            }
            return menuview!!
        }
    }
}
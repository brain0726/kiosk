package com.example.kiosk.ViewPager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.kiosk.Dialog.Coffee.CoffeeDialogFragment
import com.example.kiosk.MainActivity
import com.example.kiosk.R
import com.example.kiosk.ViewPagerAdapter
import com.example.kiosk.databinding.FragmentFirstMenuBinding
import com.example.kiosk.model.CafeData


class FirstFragment : Fragment(){

    private var mainActivity: MainActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            mainActivity = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mainActivity = null
    }


    private lateinit var viewpagerAdapter: ViewPagerAdapter

    var dataprice:Int?=null
    private var customDialogListener: CoffeeDialogFragment.CustomDialogListener? = null

    private var _binding: FragmentFirstMenuBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentFirstMenuBinding.inflate(layoutInflater)

        var fAdapter=MyGridAdapter()
        binding!!.coffee.adapter=fAdapter

        return binding!!.root
    }
    fun setDialogListener(customDialogListener: CoffeeDialogFragment.CustomDialogListener) {
        this.customDialogListener = customDialogListener
    }

    fun setData(dataList: MutableList<String>): Int {
        return dataprice ?:0
    }

    inner class MyGridAdapter():BaseAdapter(){


        var menuID= arrayOf(
            R.layout.activity_menu_americano, R.layout.activity_menu_cafelatte,
            R.layout.activity_menu_vanillalatte, R.layout.activity_menu_cafemocha
        )

        //Dataclass 정의
        val type=1//coffee
        val coffeeids= arrayOf(1,2,3,4)
        val dialogid= arrayOf(1,1,1,1)
        val dialogfreeiceids= arrayOf(1,2,3,3)
        val dialogfreehotids= arrayOf(1,1,2,2)
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
        val freehotoptionsize= arrayOf(3,2,2,2)
        val freeiceoptionsize= arrayOf(3,2,3,3)



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
                var payoption = Array(payoptionsize) { 0 }
                var freeiceoption = Array(freeiceoptionsize[p0]) { 0 }

                var freehotoption = Array(freehotoptionsize[p0]) { 0 }
                val cafeData=CafeData(type,coffeeids[p0],dialogid[p0],
                    dialogfreeiceids[p0],dialogfreehotids[p0],names[p0],
                    srcs[p0],1,prices[p0],0,payoption,
                    0,0,freehotoption,freeiceoption)

                val dialog = CoffeeDialogFragment(menuview.context)
                val args = Bundle()
                args.putParcelable("CafeData", cafeData)
                dialog.arguments = args

                dialog.setDialogListener(object : CoffeeDialogFragment.CustomDialogListener {
                    override fun onPositiveClicked(Data: CafeData) {
                        var new_cafeData=Data
//                        mainActivity?.let { activity ->
//                            activity.money += (dataprice ?: 0)
//                        }

                    }
                })
                dialog.show(parentFragmentManager, "CustomDialog")
            }
            return menuview!!
        }

    }
}
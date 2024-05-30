package com.example.kiosk.ViewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.kiosk.Dialog.Tea.TeaDialogFragment
import com.example.kiosk.R
import com.example.kiosk.databinding.FragmentSecondMenuBinding


class SecondFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding= FragmentSecondMenuBinding.inflate(layoutInflater)

        var fAdapter=MyGridAdapter()
        binding.tea.adapter=fAdapter
        return binding.root
    }
    inner class MyGridAdapter: BaseAdapter(){

        var menuID= arrayOf(
            R.layout.activity_menu_icetea, R.layout.activity_menu_jamongcha,
            R.layout.activity_menu_yujacha, R.layout.activity_menu_milktea
        )
        val imageIDs = arrayOf(
            R.drawable.icetea,
            R.drawable.jamongcha,
            R.drawable.yujacha,
            R.drawable.milktea
        )
        val names= arrayOf("아이스티","자몽차","유자차","밀크티")
        val prices=arrayOf(
            2900,4200,4200,4200
        )
        val id= arrayOf(11,12,13,14)
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
                val dialog = TeaDialogFragment()
                val args = Bundle()
                args.putInt("imageID", imageIDs[p0] )
                args.putInt("price",prices[p0])
                args.putString("Name",names[p0])
                dialog.arguments = args
                dialog.show(parentFragmentManager, "CustomDialog")
            }
            return menuview!!
        }
    }
}
package com.example.kiosk.ViewPager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.kiosk.Dialog.Tea.TeaDialog1Fragment
import com.example.kiosk.Dialog.Tea.TeaDialog2Fragment
import com.example.kiosk.Dialog.Tea.TeaDialog3Fragment
import com.example.kiosk.MainActivity
import com.example.kiosk.R
import com.example.kiosk.databinding.FragmentSecondMenuBinding
import com.example.kiosk.model.CafeData


class SecondFragment : Fragment() {

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

        val type=2//tea
        val coffeeids= arrayOf(11,12,13,14)
        val dialogid= arrayOf(1,2,2,3)
        val dialogfreeiceids= arrayOf(1,0,0,1)
        val dialogfreehotids= arrayOf(0,1,1,1)
        val srcs = arrayOf(
            R.drawable.icetea,
            R.drawable.jamongcha,
            R.drawable.yujacha,
            R.drawable.milktea
        )
        val names= arrayOf("아이스티","자몽차","유자차","밀크티")
        val prices=arrayOf(
            2900,4200,4200,4200
        )
        val payoptionsize= 8
        val freehotoptionsize= arrayOf(1,1,1,1)
        val freeiceoptionsize= arrayOf(3,1,1,3)
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
                val cafeData= CafeData(type,coffeeids[p0],dialogid[p0],
                    dialogfreeiceids[p0],dialogfreehotids[p0],names[p0],
                    srcs[p0],1,prices[p0],0,0,payoption,
                    0,0,freehotoption,freeiceoption)

                if (cafeData.dialogid==1) {
                    val dialog = TeaDialog1Fragment()
                    val args = Bundle()

                    args.putParcelable("CafeData", cafeData)

                    dialog.arguments = args

                    dialog.setDialogListener(object : TeaDialog1Fragment.CustomDialogListener {
                        override fun onPositiveClicked(Data: CafeData) {
                            var new_cafeData = Data
                            mainActivity?.let { activity ->
                                activity.cafeData = new_cafeData
                            }

                        }
                    })

                    dialog.show(parentFragmentManager, "CustomDialog")
                }
                else if (cafeData.dialogid==2) {
                    val dialog = TeaDialog2Fragment()
                    val args = Bundle()

                    args.putParcelable("CafeData", cafeData)

                    dialog.arguments = args

                    dialog.setDialogListener(object : TeaDialog2Fragment.CustomDialogListener {
                        override fun onPositiveClicked(Data: CafeData) {
                            var new_cafeData = Data
                            mainActivity?.let { activity ->
                                activity.cafeData = new_cafeData
                            }

                        }
                    })

                    dialog.show(parentFragmentManager, "CustomDialog")
                }
                else if (cafeData.dialogid==3) {
                    val dialog = TeaDialog3Fragment()
                    val args = Bundle()

                    args.putParcelable("CafeData", cafeData)

                    dialog.arguments = args

                    dialog.setDialogListener(object : TeaDialog3Fragment.CustomDialogListener {
                        override fun onPositiveClicked(Data: CafeData) {
                            var new_cafeData = Data
                            mainActivity?.let { activity ->
                                activity.cafeData = new_cafeData
                            }

                        }
                    })

                    dialog.show(parentFragmentManager, "CustomDialog")
                }


            }
            return menuview!!
        }
    }
}
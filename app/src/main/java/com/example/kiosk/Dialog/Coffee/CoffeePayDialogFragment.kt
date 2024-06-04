package com.example.kiosk.Dialog.Coffee

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.kiosk.databinding.FragmentCoffeePayDialogBinding
import com.example.kiosk.model.CafeData


class CoffeePayDialogFragment : DialogFragment() {

    interface CustomDialogListener{
        fun onPositiveClicked(Data:CafeData);
    }
    private var customDialogListener: CustomDialogListener?=null

    private var _binding:FragmentCoffeePayDialogBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCoffeePayDialogBinding.inflate(layoutInflater)
        return binding!!.root
    }

    private var cafeData: CafeData? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plusID = arrayOf(binding!!.plus1,binding!!.plus2,binding!!.plus3,binding!!.plus4,binding!!.plus5,binding!!.plus6,binding!!.plus7,binding!!.plus8,binding!!.plus9)
        val countID = arrayOf(binding!!.count1,binding!!.count2,binding!!.count3,binding!!.count4,binding!!.count5,binding!!.count6,binding!!.count7,binding!!.count8,binding!!.count9)
        val minusID = arrayOf(binding!!.minus1,binding!!.minus2,binding!!.minus3,binding!!.minus4,binding!!.minus5,binding!!.minus6,binding!!.minus7,binding!!.minus8,binding!!.minus9)


        arguments?.let {
            cafeData = it.getParcelable("CafeData")
        }
        binding!!.coffeeName.text=cafeData!!.name


        for (i in 0..plusID.size-1 step 1){
            plusID[i].setOnClickListener {
                var count = countID[i].text.toString().toInt()
                cafeData!!.payoption[i]+=1
                count += 1
                cafeData!!.price += plusID[i].tag.toString().toInt()
                countID[i].text="$count"

            }
            minusID[i].setOnClickListener {
                var count = countID[i].text.toString().toInt()
                if (count>0){
                    count-=1
                    cafeData!!.payoption[i]-=1
                    cafeData!!.price -= minusID[i].tag.toString().toInt()
                }
                countID[i].text="$count"
            }
        }

        binding!!.payAccept.setOnClickListener {
            customDialogListener?.let { listener ->
                cafeData.let {Data ->
                    Data?.let { it1 -> listener.onPositiveClicked(it1) }
                }
            }
            dialog?.dismiss()
        }
        binding!!.payCancel.setOnClickListener {
            dialog?.dismiss()
        }
    }
    override fun onStart() {
        super.onStart()
        val metrics: DisplayMetrics = resources.displayMetrics
        val width = (metrics.widthPixels * 0.9).toInt() // 화면 너비의 90%
        val height = (metrics.heightPixels * 0.8).toInt() // 화면 높이의 80%
        dialog?.window?.setLayout(width, height)
    }
    fun setDialogListener(customDialogListener: CustomDialogListener) {
        this.customDialogListener = customDialogListener
    }

}
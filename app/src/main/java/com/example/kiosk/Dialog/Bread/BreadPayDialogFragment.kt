package com.example.kiosk.Dialog.Bread

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

import com.example.kiosk.R
import com.example.kiosk.databinding.FragmentBreadPayDialogBinding
import com.example.kiosk.databinding.FragmentCoffeePayDialogBinding
import com.example.kiosk.databinding.FragmentTeaPayDialogBinding

class BreadPayDialogFragment : DialogFragment() {

    interface CustomDialogListener{
        fun onPositiveClicked(price:Int);
    }
    private var customDialogListener: CustomDialogListener?=null

    private var _binding: FragmentBreadPayDialogBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBreadPayDialogBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plusID = arrayOf(binding!!.plus1)
        val countID = arrayOf(binding!!.count1)
        val minusID = arrayOf(binding!!.minus1)

        var price=0
        val name = arguments?.getString("name")
        name?.let { Log.d("MyTag", it) }
        binding!!.coffeeName.text=name


        for (i in 0..plusID.size-1 step 1){
            plusID[i].setOnClickListener {
                var count = countID[i].text.toString().toInt()
                count+=1
                price += plusID[i].tag.toString().toInt()
                countID[i].text="$count"

            }
            minusID[i].setOnClickListener {
                var count = countID[i].text.toString().toInt()
                if (count>0){
                    count-=1
                    price -= minusID[i].tag.toString().toInt()
                }
                countID[i].text="$count"
            }
        }

        binding!!.payAccept.setOnClickListener {
            customDialogListener?.let { listener ->
                price.let {price ->
                    listener.onPositiveClicked(price)
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
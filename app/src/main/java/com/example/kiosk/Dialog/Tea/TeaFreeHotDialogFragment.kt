package com.example.kiosk.Dialog.Tea

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.kiosk.R
import com.example.kiosk.databinding.FragmentCoffeePayDialogBinding
import com.example.kiosk.databinding.FragmentTeaPayDialogBinding

class TeaFreeHotDialogFragment : DialogFragment() {

    interface CustomDialogListener{
        fun onPositiveClicked(price:Int);
    }
    private var customDialogListener: CustomDialogListener?=null

    private var _binding: FragmentTeaPayDialogBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTeaPayDialogBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plusID = arrayOf(binding!!.plus1,binding!!.plus2,binding!!.plus3,binding!!.plus4,binding!!.plus5,binding!!.plus6,binding!!.plus7,binding!!.plus8,binding!!.plus9)
        val countID = arrayOf(binding!!.count1,binding!!.count2,binding!!.count3,binding!!.count4,binding!!.count5,binding!!.count6,binding!!.count7,binding!!.count8,binding!!.count9)
        val minusID = arrayOf(binding!!.minus1,binding!!.minus2,binding!!.minus3,binding!!.minus4,binding!!.minus5,binding!!.minus6,binding!!.minus7,binding!!.minus8,binding!!.minus9)

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
        dialog?.window?.setLayout((resources.displayMetrics.widthPixels * 1).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
    fun setDialogListener(customDialogListener: CustomDialogListener) {
        this.customDialogListener = customDialogListener
    }

}
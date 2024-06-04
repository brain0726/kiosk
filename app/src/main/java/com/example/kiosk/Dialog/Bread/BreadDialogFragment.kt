package com.example.kiosk.Dialog.Bread

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.kiosk.Dialog.Coffee.CoffeeDialog1Fragment.CustomDialogListener
import com.example.kiosk.Dialog.Coffee.CoffeePayDialogFragment
import com.example.kiosk.databinding.FragmentBreadDialogBinding
import com.example.kiosk.databinding.FragmentTeaDialog3Binding
import com.example.kiosk.model.CafeData

class BreadDialogFragment : DialogFragment() {

    interface CustomDialogListener{
        fun onPositiveClicked(data: CafeData);
    }
    private var customDialogListener:CustomDialogListener?=null

    private var _binding: FragmentBreadDialogBinding? = null
    private val binding get() = _binding

    private var cafeData: CafeData? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentBreadDialogBinding.inflate(layoutInflater)

        arguments?.let {
            cafeData = it.getParcelable("CafeData")
        }
        binding!!.coffeeImage.setImageResource(cafeData!!.src)
        val price=cafeData!!.price
        binding!!.coffeePrice.text="$price"
        binding!!.countPrice.text="0"
        binding!!.coffeeName.text = cafeData!!.name

        binding!!.payButton.setOnClickListener {
            val dialog = BreadPayDialogFragment()

            val args = Bundle()
            args.putParcelable("CafeData", cafeData)
            dialog.arguments=args
            dialog.setDialogListener(object :BreadPayDialogFragment.CustomDialogListener {
                override fun onPositiveClicked(Data: CafeData) {
                    cafeData=Data
                    cafeData!!.finalprice = cafeData!!.price!!*cafeData!!.count
                    val finalprice=cafeData!!.finalprice
                    binding!!.countPrice.text="$finalprice"
                }
            })
            dialog.show(childFragmentManager, "CoffeePayDialogFragment")
        }

        binding!!.minusButton.setOnClickListener {
            if(cafeData!!.count>1){
                cafeData!!.count-=1
            }
            binding!!.countNum.text=cafeData!!.count.toString()
            cafeData!!.finalprice= cafeData!!.price *cafeData!!.count
            val finalprice=cafeData!!.finalprice
            binding!!.countPrice.text="$finalprice"
        }

        binding!!.plusButton.setOnClickListener {
            cafeData!!.count += 1

            binding!!.countNum.text=cafeData!!.count.toString()
            cafeData!!.finalprice= cafeData!!.price *cafeData!!.count
            val finalprice=cafeData!!.finalprice
            binding!!.countPrice.text="$finalprice"
        }
        binding!!.cancleButton.setOnClickListener {
            dialog?.dismiss()
        }
        binding?.acceptButton?.setOnClickListener {

            customDialogListener?.let { listener ->
                cafeData?.let {Data ->
                    listener.onPositiveClicked(Data)
                }
            }
            dialog?.dismiss()
        }
        return binding!!.root
    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout((resources.displayMetrics.widthPixels * 1).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
    fun setDialogListener(listener: CustomDialogListener) {
        this.customDialogListener = listener
    }
}
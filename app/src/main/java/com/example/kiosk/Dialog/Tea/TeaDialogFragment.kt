package com.example.kiosk.Dialog.Tea

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.example.kiosk.Dialog.Coffee.CoffeeFreeHotDialogFragment
import com.example.kiosk.Dialog.Coffee.CoffeeFreeIceDialogFragment
import com.example.kiosk.Dialog.Coffee.CoffeePayDialogFragment
import com.example.kiosk.R
import com.example.kiosk.databinding.FragmentCoffeeDialogBinding
import com.example.kiosk.databinding.FragmentTeaDialogBinding
import com.example.kiosk.model.CafeData

class TeaDialogFragment : DialogFragment() {

    interface CustomDialogListener{
        fun onPositiveClicked(price:Int);
    }
    private var customDialogListener:CustomDialogListener?=null

    private var _binding: FragmentTeaDialogBinding? = null
    private val binding get() = _binding

    private var cafeData: CafeData? = null

    // 사용할 때 null 체크를 해야 함
    private fun notifyListener(price: Int) {
        customDialogListener?.onPositiveClicked(price)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentTeaDialogBinding.inflate(layoutInflater)

        arguments?.let {
            cafeData = it.getParcelable("CafeData")
        }
        binding!!.coffeeImage.setImageResource(cafeData!!.src)
        if(cafeData!!.price!=null){
            val price=cafeData!!.price
            binding!!.coffeePrice.text="$price"
        }
        binding!!.countPrice.text="0"
        binding!!.coffeeName.text = cafeData!!.name


        binding!!.largeButton.setOnClickListener{
            selectsizeButton(binding!!.largeButton)
            deselectsizeButton(binding!!.extraButton)
            if (cafeData!!.price != null) {
                cafeData!!.count=binding!!.countNum.text.toString().toInt()
                if(cafeData!!.size==2){
                    cafeData!!.price = cafeData!!.price - 1000
                    cafeData!!.finalprice = cafeData!!.price*cafeData!!.count
                }
                else {
                    cafeData!!.finalprice = cafeData!!.price * cafeData!!.count
                }
                cafeData!!.size=1 //largesize
                val finalprice=cafeData!!.finalprice
                binding!!.countPrice.text="$finalprice"
            }
        }
        binding!!.extraButton.setOnClickListener {
            selectsizeButton(binding!!.extraButton)
            deselectsizeButton(binding!!.largeButton)
            if (cafeData!!.price != null) {
                cafeData!!.count=binding!!.countNum.text.toString().toInt()
                cafeData!!.price = cafeData!!.price + 1000
                cafeData!!.finalprice = cafeData!!.price*cafeData!!.count
                val finalprice=cafeData!!.finalprice
                cafeData!!.size=2 //extrasize
                binding!!.countPrice.text="$finalprice"
            }
        }

        binding?.payButton?.setOnClickListener {

            val dialog = CoffeePayDialogFragment()

            val args = Bundle()
            args.putParcelable("CafeData", cafeData)
            dialog.arguments=args
            dialog.setDialogListener(object : CoffeePayDialogFragment.CustomDialogListener {
                override fun onPositiveClicked(Data: CafeData) {
                    cafeData=Data
                    cafeData!!.finalprice = cafeData!!.price!!*cafeData!!.count
                    val finalprice=cafeData!!.finalprice
                    binding!!.countPrice.text="$finalprice"
                }
            })
            dialog.show(childFragmentManager, "CoffeePayDialogFragment")
        }
        binding!!.freeButton.setOnClickListener {
            if(cafeData!!.ice==1) {//ice
                val dialog = CoffeeFreeIceDialogFragment()

                val args = Bundle()
                args.putParcelable("CafeData", cafeData)
                dialog.arguments = args
                dialog.setDialogListener(object : CoffeeFreeIceDialogFragment.CustomDialogListener {
                    override fun onPositiveClicked(Data: CafeData) {
                        cafeData=Data
                        cafeData!!.finalprice = cafeData!!.price!!*cafeData!!.count
                        val finalprice=cafeData!!.finalprice
                        binding!!.countPrice.text="$finalprice"
                    }
                })
                dialog.show(childFragmentManager, "CoffeePayDialogFragment")
            }
            else if(cafeData!!.ice==2){//hot
                val dialog = CoffeeFreeHotDialogFragment()

                val args = Bundle()
                args.putParcelable("CafeData", cafeData)
                dialog.arguments = args
                dialog.setDialogListener(object : CoffeeFreeHotDialogFragment.CustomDialogListener {
                    override fun onPositiveClicked(Data: CafeData) {
                        cafeData=Data
                        cafeData!!.finalprice = cafeData!!.price!!*cafeData!!.count
                        val finalprice=cafeData!!.finalprice
                        binding!!.countPrice.text="$finalprice"
                    }
                })
                dialog.show(childFragmentManager, "CoffeePayDialogFragment")
            }
        }

        binding!!.minusButton.setOnClickListener {
            var count=binding!!.countNum.text.toString().toInt()
            if(count>1){
                count-=1
            }
            binding!!.countNum.text=count.toString()
            sizeprice= price!! *count
            binding!!.countPrice.text="$sizeprice"
        }

        binding!!.plusButton.setOnClickListener {
            var count=binding!!.countNum.text.toString().toInt()
            count += 1

            binding!!.countNum.text=count.toString()
            sizeprice= price!! *count
            binding!!.countPrice.text="$sizeprice"
        }
        binding!!.cancleButton.setOnClickListener {
            dialog?.dismiss()
        }
        binding?.acceptButton?.setOnClickListener {

            customDialogListener?.let { listener ->
                sizeprice?.let {price ->
                    listener.onPositiveClicked(price)
                }
            }
            dialog?.dismiss()
        }
        return binding!!.root
    }

    private fun selectsizeButton(button: ImageButton) {
        button.alpha = 1.0f // 완전 불투명
    }

    private fun deselectsizeButton(button: ImageButton) {
        button.alpha = 0.5f // 약간 투명
    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout((resources.displayMetrics.widthPixels * 1).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
    fun setDialogListener(listener:CustomDialogListener) {
        this.customDialogListener = listener
    }

}
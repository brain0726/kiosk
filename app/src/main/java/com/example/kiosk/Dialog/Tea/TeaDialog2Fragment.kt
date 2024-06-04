package com.example.kiosk.Dialog.Tea

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.example.kiosk.Dialog.Coffee.CoffeeFreeHotDialog1Fragment
import com.example.kiosk.Dialog.Coffee.CoffeeFreeIceDialog1Fragment
import com.example.kiosk.Dialog.Coffee.CoffeePayDialogFragment
import com.example.kiosk.databinding.FragmentTeaDialog1Binding
import com.example.kiosk.databinding.FragmentTeaDialog2Binding
import com.example.kiosk.model.CafeData

class TeaDialog2Fragment : DialogFragment() {

    interface CustomDialogListener{
        fun onPositiveClicked(data:CafeData);
    }
    private var customDialogListener:CustomDialogListener?=null

    private var _binding: FragmentTeaDialog2Binding? = null
    private val binding get() = _binding

    private var cafeData: CafeData? = null

    // 사용할 때 null 체크를 해야 함
    private fun notifyListener(data: CafeData) {
        customDialogListener?.onPositiveClicked(data)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentTeaDialog2Binding.inflate(layoutInflater)

        arguments?.let {
            cafeData = it.getParcelable("CafeData")
        }
        binding!!.coffeeImage.setImageResource(cafeData!!.src)
        if(cafeData!!.price!=null){
            val price=cafeData!!.price
            binding!!.coffeePrice.text="$price"
        }
        cafeData!!.ice=2
        cafeData!!.size=1
        binding!!.countPrice.text="0"
        binding!!.coffeeName.text = cafeData!!.name


        binding?.payButton?.setOnClickListener {

            val dialog = TeaPayDialogFragment()

            val args = Bundle()
            args.putParcelable("CafeData", cafeData)
            dialog.arguments=args
            dialog.setDialogListener(object : TeaPayDialogFragment.CustomDialogListener {
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
                val dialog = TeaFreeIceDialogFragment()

                val args = Bundle()
                args.putParcelable("CafeData", cafeData)
                dialog.arguments = args
                dialog.setDialogListener(object : TeaFreeIceDialogFragment.CustomDialogListener {
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
                val dialog = TeaFreeHotDialogFragment()

                val args = Bundle()
                args.putParcelable("CafeData", cafeData)
                dialog.arguments = args
                dialog.setDialogListener(object : TeaFreeHotDialogFragment.CustomDialogListener {
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
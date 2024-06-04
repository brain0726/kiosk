package com.example.kiosk.Dialog.Coffee

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.example.kiosk.R
import com.example.kiosk.databinding.FragmentCoffeeDialog1Binding
import com.example.kiosk.model.CafeData

class CoffeeDialog1Fragment(context: Context) : DialogFragment() {

    interface CustomDialogListener{
        fun onPositiveClicked(price: CafeData);
    }
    private var customDialogListener:CustomDialogListener?=null

    private var _binding: FragmentCoffeeDialog1Binding? = null
    private val binding get() = _binding

    private var cafeData: CafeData? = null

    // 사용할 때 null 체크를 해야 함
    private fun notifyListener(Data: CafeData) {
        customDialogListener?.onPositiveClicked(Data)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentCoffeeDialog1Binding.inflate(layoutInflater)

        arguments?.let {
            cafeData = it.getParcelable("CafeData")
        }
        binding!!.coffeeImage.setImageResource(cafeData!!.src)
        val price=cafeData!!.price
        binding!!.coffeePrice.text="$price"
        binding!!.countPrice.text="0"
        binding!!.coffeeName.text = cafeData!!.name

        binding!!.iceButton.setOnClickListener{
            selectdegreeButton(binding!!.iceButton)
            deselectdegreeButton(binding!!.hotButton)
            cafeData!!.ice=1
        }
        binding!!.hotButton.setOnClickListener{
            selectdegreeButton(binding!!.hotButton)
            deselectdegreeButton(binding!!.iceButton)
            cafeData!!.ice=2
        }

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
                if(cafeData!!.dialogfreeiceid==1) {
                    val dialog = CoffeeFreeIceDialog1Fragment()

                    val args = Bundle()
                    args.putParcelable("CafeData", cafeData)
                    dialog.arguments = args
                    dialog.setDialogListener(object :
                        CoffeeFreeIceDialog1Fragment.CustomDialogListener {
                        override fun onPositiveClicked(Data: CafeData) {
                            cafeData = Data
                            val finalprice = cafeData!!.finalprice
                            binding!!.countPrice.text = "$finalprice"
                        }
                    })
                    dialog.show(childFragmentManager, "CoffeePayDialogFragment")
                }
                else if(cafeData!!.dialogfreeiceid==2) {
                    val dialog = CoffeeFreeIceDialog2Fragment()

                    val args = Bundle()
                    args.putParcelable("CafeData", cafeData)
                    dialog.arguments = args
                    dialog.setDialogListener(object :
                        CoffeeFreeIceDialog2Fragment.CustomDialogListener {
                        override fun onPositiveClicked(Data: CafeData) {
                            cafeData = Data
                            val finalprice = cafeData!!.finalprice
                            binding!!.countPrice.text = "$finalprice"
                        }
                    })
                    dialog.show(childFragmentManager, "CoffeePayDialogFragment")
                }
                else if(cafeData!!.dialogfreeiceid==3) {
                    val dialog = CoffeeFreeIceDialog3Fragment()

                    val args = Bundle()
                    args.putParcelable("CafeData", cafeData)
                    dialog.arguments = args
                    dialog.setDialogListener(object :
                        CoffeeFreeIceDialog3Fragment.CustomDialogListener {
                        override fun onPositiveClicked(Data: CafeData) {
                            cafeData = Data
                            val finalprice = cafeData!!.finalprice
                            binding!!.countPrice.text = "$finalprice"
                        }
                    })
                    dialog.show(childFragmentManager, "CoffeePayDialogFragment")
                }
            }
            else if(cafeData!!.ice==2){//hot
                if(cafeData!!.dialogfreehotid==1) {
                    val dialog = CoffeeFreeHotDialog1Fragment()

                    val args = Bundle()
                    args.putParcelable("CafeData", cafeData)
                    dialog.arguments = args
                    dialog.setDialogListener(object :
                        CoffeeFreeHotDialog1Fragment.CustomDialogListener {
                        override fun onPositiveClicked(Data: CafeData) {
                            cafeData = Data
                            val finalprice = cafeData!!.finalprice
                            binding!!.countPrice.text = "$finalprice"
                        }
                    })
                    dialog.show(childFragmentManager, "CoffeePayDialogFragment")
                }
                else if(cafeData!!.dialogfreehotid==2) {
                    val dialog = CoffeeFreeHotDialog2Fragment()

                    val args = Bundle()
                    args.putParcelable("CafeData", cafeData)
                    dialog.arguments = args
                    dialog.setDialogListener(object :
                        CoffeeFreeHotDialog2Fragment.CustomDialogListener {
                        override fun onPositiveClicked(Data: CafeData) {
                            cafeData = Data
                            val finalprice = cafeData!!.finalprice
                            binding!!.countPrice.text = "$finalprice"
                        }
                    })
                    dialog.show(childFragmentManager, "CoffeePayDialogFragment")
                }
            }
        }

        binding!!.minusButton.setOnClickListener {
            if(cafeData!!.count>1){
                cafeData!!.count-=1
            }
            binding!!.countNum.text=cafeData!!.count.toString()
            val finalprice=cafeData!!.price
            binding!!.countPrice.text="$finalprice"
        }

        binding!!.plusButton.setOnClickListener {
            cafeData!!.count += 1

            binding!!.countNum.text=cafeData!!.count.toString()
            val finalprice=cafeData!!.price*cafeData!!.count
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

    private fun selectdegreeButton(button: Button) {
        button.setBackgroundColor(Color.parseColor("#FFFFFF"))
        if (button.id == binding!!.iceButton.id) {
            button.setBackgroundResource(R.drawable.ice_button_border)
            button.setTextColor(Color.parseColor("#1478FF"))
        } else {
            button.setBackgroundResource(R.drawable.hot_button_border)
            button.setTextColor(Color.parseColor("#FF98A3"))
        }
    }
    private fun deselectdegreeButton(button: Button) {
        button.setBackgroundResource(R.drawable.button_deselected)
        button.setTextColor(Color.parseColor("#808080"))
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
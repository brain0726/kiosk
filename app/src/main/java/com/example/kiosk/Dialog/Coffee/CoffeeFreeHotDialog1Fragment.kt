package com.example.kiosk.Dialog.Coffee

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.kiosk.databinding.FragmentCoffeeFreeHotDialog1Binding
import com.example.kiosk.model.CafeData

class CoffeeFreeHotDialog1Fragment : DialogFragment() {

    interface CustomDialogListener{
        fun onPositiveClicked(Data: CafeData);
    }
    private var customDialogListener: CustomDialogListener?=null

    private var _binding: FragmentCoffeeFreeHotDialog1Binding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoffeeFreeHotDialog1Binding.inflate(layoutInflater)
        return binding!!.root
    }

    private var cafeData: CafeData? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            cafeData = it.getParcelable("CafeData")
        }
        binding!!.coffeeName.text=cafeData!!.name

        binding!!.density.setOnClickListener {
            if(cafeData!!.icefreeoption[0]==0){
                deselectsizeButton(binding!!.density)
                cafeData!!.icefreeoption[0]=1
            }
            else{
                selectsizeButton(binding!!.density)
                cafeData!!.icefreeoption[0]=0
            }
        }

        binding!!.hot.setOnClickListener {
            if(cafeData!!.icefreeoption[1]==0){
                deselectsizeButton(binding!!.hot)
                cafeData!!.icefreeoption[1]=1
            }
            else{
                selectsizeButton(binding!!.hot)
                cafeData!!.icefreeoption[1]=0
            }
        }

        binding!!.water.setOnClickListener {
            if(cafeData!!.icefreeoption[2]==0){
                deselectsizeButton(binding!!.water)
                cafeData!!.icefreeoption[2]=1
            }
            else{
                selectsizeButton(binding!!.water)
                cafeData!!.icefreeoption[2]=2
            }
        }



        binding!!.freeAccept.setOnClickListener {
            customDialogListener?.let { listener ->
                cafeData!!.let {Data ->
                    listener.onPositiveClicked(Data)
                }
            }
            dialog?.dismiss()
        }
        binding!!.freeCancel.setOnClickListener {
            dialog?.dismiss()
        }
    }
    private fun selectsizeButton(button: Button) {
        button.alpha = 1.0f // 완전 불투명
    }
    override fun onStart() {
        super.onStart()
        val metrics: DisplayMetrics = resources.displayMetrics
        val width = (metrics.widthPixels * 0.9).toInt() // 화면 너비의 90%
        val height = (metrics.heightPixels * 0.8).toInt() // 화면 높이의 80%
        dialog?.window?.setLayout(width, height)
    }

    private fun deselectsizeButton(button: Button) {
        button.alpha = 0.5f // 약간 투명
    }

    fun setDialogListener(listener: CustomDialogListener) {
        this.customDialogListener = listener
    }
}
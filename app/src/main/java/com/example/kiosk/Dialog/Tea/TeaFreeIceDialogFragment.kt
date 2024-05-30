package com.example.kiosk.Dialog.Tea

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.kiosk.R
import com.example.kiosk.databinding.FragmentCoffeeFreeIceDialogBinding
import com.example.kiosk.databinding.FragmentTeaFreeIceDialogBinding

class TeaFreeIceDialogFragment : DialogFragment() {

    private var _binding: FragmentTeaFreeIceDialogBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeaFreeIceDialogBinding.inflate(layoutInflater)
        return binding!!.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)


        val name = arguments?.getString("name")
        name?.let { Log.d("MyTag", it) }
        binding!!.coffeeName.text=name

        var ice:Int?=null


        var iceMinBoolean:Boolean=false
        binding!!.iceMin.setOnClickListener {
            if(iceMinBoolean){
                ice=null
                deselectsizeButton(binding!!.iceMin)
                iceMinBoolean=true
            }
            else {
                ice = 1
                selectsizeButton(binding!!.iceMin)
                deselectsizeButton(binding!!.iceMax)
                iceMinBoolean=false
            }
        }

        var iceMaxBoolean:Boolean=false

        binding!!.iceMax.setOnClickListener {
            if(iceMaxBoolean){
                ice=null
                deselectsizeButton(binding!!.iceMax)
                iceMaxBoolean=true
            }
            else {
                ice = 2
                selectsizeButton(binding!!.iceMax)
                deselectsizeButton(binding!!.iceMin)
                iceMaxBoolean=false
            }
        }


        binding!!.freeAccept.setOnClickListener {
//            customDialogListener?.let { listener ->
//                price.let {price ->
//                    listener.onPositiveClicked(price)
//                }
//            }
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
}
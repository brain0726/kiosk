package com.example.kiosk.Dialog.Bread

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.kiosk.Dialog.Coffee.CoffeePayDialogFragment
import com.example.kiosk.databinding.FragmentBreadDialogBinding
import com.example.kiosk.databinding.FragmentTeaDialogBinding

class BreadDialogFragment : DialogFragment() {

    private var _binding: FragmentBreadDialogBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentBreadDialogBinding.inflate(layoutInflater)

        val imageID = arguments?.getInt("imageID")
        val price=arguments?.getInt("price")
        val name=arguments?.getString("Name")
        if (imageID != null) {
            binding!!.coffeeImage.setImageResource(imageID)
        }
        if (price != null) {
            binding!!.coffeePrice.text="$price"
        }
        if(name != null) {
            binding!!.coffeeName.text = name
        }

        binding!!.payButton.setOnClickListener {
            val dialog = BreadPayDialogFragment()

            val args = Bundle()
            args.putString("name",name)
            dialog.arguments=args
            /*dialog.setDialogListener(object : BreadPayDialogFragment.CustomDialogListener {
                override fun onPositiveClicked(payprice: Int) {
                    price = price?.plus(payprice)
                    val count=binding!!.countNum.text.toString().toInt()
                    sizeprice = price!!*count
                    binding!!.countPrice.text="$sizeprice"
                }
            })*/
            dialog.show(childFragmentManager, "CoffeePayDialogFragment")
        }

        binding!!.cancleButton.setOnClickListener {
            dialog?.dismiss()
        }
        binding!!.acceptButton.setOnClickListener {
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
}
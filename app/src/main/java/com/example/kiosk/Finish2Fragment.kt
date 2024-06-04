package com.example.kiosk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.kiosk.Dialog.Coffee.CoffeePayDialogFragment
import com.example.kiosk.databinding.FragmentFinish1Binding
import com.example.kiosk.databinding.FragmentFinish2Binding
import com.example.kiosk.model.CafeData

class Finish2Fragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= FragmentFinish2Binding.inflate(layoutInflater)
        var price=0
        arguments?.let {
            price = it.getInt("price")
        }


        binding.oneButton.setOnClickListener {
            val dialog = FinishoneFragment()

            val args = Bundle()
            args.putInt("price",price)
            dialog.arguments=args

            dialog.show(childFragmentManager, "CoffeePayDialogFragment")
        }
        binding.twoButton.setOnClickListener {
            val dialog = FinishtwoFragment()

            val args = Bundle()
            args.putInt("price",price)
            dialog.arguments=args

            dialog.show(childFragmentManager, "CoffeePayDialogFragment")

        }

        return binding.root
    }
}
package com.example.kiosk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.kiosk.Dialog.Coffee.CoffeePayDialogFragment
import com.example.kiosk.databinding.FragmentFinish1Binding
import com.example.kiosk.model.CafeData

class Finish1Fragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding=FragmentFinish1Binding.inflate(layoutInflater)
        var price=0
        arguments?.let {
            price = it.getInt("price")
        }


        binding.cardButton.setOnClickListener {
            val dialog = Finish2Fragment()

            val args = Bundle()
            args.putInt("price",price)
            dialog.arguments=args

            dialog.show(childFragmentManager, "CoffeePayDialogFragment")
        }
        binding.payButton.setOnClickListener {
            val dialog = Finish2Fragment()

            val args = Bundle()
            args.putInt("price",price)
            dialog.arguments=args

            dialog.show(childFragmentManager, "CoffeePayDialogFragment")

        }

        return binding.root
    }
}
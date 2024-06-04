package com.example.kiosk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.kiosk.Dialog.Coffee.CoffeePayDialogFragment
import com.example.kiosk.databinding.FragmentFinish1Binding
import com.example.kiosk.databinding.FragmentFinishoneBinding
import com.example.kiosk.databinding.FragmentFinishtwoBinding


class FinishtwoFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= FragmentFinishtwoBinding.inflate(layoutInflater)
        var price=0
        arguments?.let {
            price = it.getInt("price")
        }
        binding.totalAmountTextView.text = "총 결제 금액: $price"



        binding.paymentButton.setOnClickListener {
            val enteredAmount = binding.amountEditText.text.toString().toIntOrNull() ?: 0
            val enteredpeople = binding.humanEditText.text.toString().toIntOrNull() ?: 0
            if (enteredAmount*enteredpeople >= price) {
                Toast.makeText(activity, "결제 성공", Toast.LENGTH_SHORT).show()
                dismiss()
            } else {
                Toast.makeText(activity, "결제 실패 결제 해야하는 금액: $price", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}
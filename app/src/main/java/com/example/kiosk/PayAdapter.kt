package com.example.kiosk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kiosk.databinding.ItemCafeDataBinding
import com.example.kiosk.databinding.PayItemBinding
import com.example.kiosk.model.CafeData

class PayAdapter(val items: MutableList<CafeData>, private val payActivity: Pay) : RecyclerView.Adapter<PayAdapter.CafeDataViewHolder>() {

    class CafeDataViewHolder(private val binding: PayItemBinding, private val payActivity: Pay) : RecyclerView.ViewHolder(binding.root)  {

        fun bind(cafeData: CafeData) {
            binding.coffeeImage.setImageResource(cafeData.src)
            binding.coffeeName.text = cafeData.name.toString()
            binding.coffeePrice.text=cafeData.price.toString()
            binding.countTextView.text=cafeData.count.toString()
            // Plus and Minus button functionality
            binding.minusButton.setOnClickListener {
                if (cafeData.count > 1) {
                    val newCount = cafeData.count - 1
                    binding.countTextView.text = newCount.toString()
                    cafeData.count = newCount
                    payActivity.updateMoney(cafeData.price, false)

                }
                else if (cafeData.count == 1) {
                    val position = adapterPosition
                    payActivity.updateMoney(cafeData.price, false)
                    if (position != RecyclerView.NO_POSITION) {
                        payActivity.removeItem(position)
                    }
                }
            }

            binding.plusButton.setOnClickListener {
                val newCount = cafeData.count + 1
                binding.countTextView.text = newCount.toString()
                cafeData.count = newCount
                payActivity.updateMoney(cafeData.price, true)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeDataViewHolder {
        val binding = PayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CafeDataViewHolder(binding, payActivity)
    }




    override fun onBindViewHolder(holder: CafeDataViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun addItem(cafeData: CafeData) {
        items.add(cafeData)
        notifyItemInserted(items.size - 1)
    }
}
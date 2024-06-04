package com.example.kiosk

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kiosk.databinding.ItemCafeDataBinding
import com.example.kiosk.model.CafeData

class CafeDataAdapter(val items: MutableList<CafeData>, private val mainActivity: MainActivity) : RecyclerView.Adapter<CafeDataAdapter.CafeDataViewHolder>() {

    class CafeDataViewHolder(private val binding: ItemCafeDataBinding, private val mainActivity: MainActivity) : RecyclerView.ViewHolder(binding.root)  {

        fun bind(cafeData: CafeData) {
            binding.coffeeImage.setImageResource(cafeData.src)
            binding.countTextView.text = cafeData.count.toString()

            // Plus and Minus button functionality
            binding.minusButton.setOnClickListener {
                if (cafeData.count > 1) {
                    val newCount = cafeData.count - 1
                    binding.countTextView.text = newCount.toString()
                    cafeData.count = newCount
                    mainActivity.updateMoney(cafeData.price, false)

                }
                else if (cafeData.count == 1) {
                    val position = adapterPosition
                    mainActivity.updateMoney(cafeData.price, false)
                    if (position != RecyclerView.NO_POSITION) {
                        mainActivity.removeItem(position)
                    }
                }
            }

            binding.plusButton.setOnClickListener {
                val newCount = cafeData.count + 1
                binding.countTextView.text = newCount.toString()
                cafeData.count = newCount
                mainActivity.updateMoney(cafeData.price, true)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeDataViewHolder {
        val binding = ItemCafeDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CafeDataViewHolder(binding, mainActivity)
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
package com.example.kiosk

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kiosk.Dialog.Tea.TeaDialog3Fragment
import com.example.kiosk.databinding.ActivityMainBinding
import com.example.kiosk.databinding.ActivityPayBinding
import com.example.kiosk.databinding.PayItemBinding
import com.example.kiosk.model.CafeData

class Pay: AppCompatActivity() {
    private lateinit var payAdapter: PayAdapter
    private var _binding: ActivityPayBinding? = null
    private val binding get() =_binding
    var money=0
    var cafeData: CafeData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityPayBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val cafeDataList = intent.getParcelableArrayListExtra<CafeData>("cafeDataList") ?: mutableListOf()
        money = intent.getIntExtra("price", 0)
        binding!!.money.text="$money"

        payAdapter = PayAdapter(cafeDataList!!, this)
        binding!!.recyclerview.layoutManager = LinearLayoutManager(this)
        binding!!.recyclerview.adapter = payAdapter


        binding!!.beforebutton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding!!.afterbutton.setOnClickListener{
            val dialog = Finish1Fragment()
            val args = Bundle()

            args.putInt("price", money)
            dialog.arguments = args


            dialog.show(supportFragmentManager, "CustomDialog")

        }

    }
    fun updateMoney(amount: Int, isAddition: Boolean) {
        if (isAddition) {
            money += amount
        } else {
            money -= amount
        }
        binding?.money?.text = money.toString()
    }
    fun addCafeData(data: CafeData) {
        cafeData = data
    }
    fun removeItem(position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            payAdapter.items.removeAt(position) // 아이템 삭제
            payAdapter.notifyItemRemoved(position) // 어댑터에 변경 사항 알림
        }
    }

}
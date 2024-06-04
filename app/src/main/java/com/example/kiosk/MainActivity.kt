package com.example.kiosk

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kiosk.databinding.ActivityMainBinding
import com.example.kiosk.databinding.FragmentFirstMenuBinding
import com.example.kiosk.model.CafeData
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(){
    private lateinit var cafeDataAdapter: CafeDataAdapter
    private val cafeDataArray: MutableList<CafeData> = mutableListOf()
    private var _binding: ActivityMainBinding? = null
    private val binding get() =_binding

    var money = 0
    var cafeData: CafeData? = null
        set(value) {
            field = value
            value?.let { cafeDataArray.add(it) }
            value?.let { cafeDataAdapter.notifyItemInserted(cafeDataArray.size - 1) }
            money+= value!!.price*value.count
            binding!!.moneysum.text="$money"

        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        _binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.homebutton.setOnClickListener {
            moveToStartActivity()
        }

        //뷰페이저 어뎁터 연결
        binding!!.viewpager2.adapter=ViewPagerAdapter(this)

        //탭 레이아웃 설정
        val tabTitles = arrayOf("coffee", "tea", "bread")

        //탭 레이아웃과 뷰페이저 join
        //https://huiveloper.tistory.com/10
        TabLayoutMediator(binding!!.tab, binding!!.viewpager2) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        cafeDataAdapter = CafeDataAdapter(cafeDataArray,this)
        binding!!.recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding!!.recyclerview.adapter = cafeDataAdapter

        binding!!.nextButton.setOnClickListener {
            val intent = Intent(this, Pay::class.java)
            intent.putParcelableArrayListExtra("cafeDataList", ArrayList(cafeDataArray))

            val moneySumText = binding!!.moneysum.text?.toString()
            if (!moneySumText.isNullOrEmpty()) {
                intent.putExtra("price", moneySumText.toInt())
            } else {
                // Handle the case when moneysum is null or empty
                intent.putExtra("price", 0) // Default value, or handle appropriately
            }

            startActivity(intent)
        }

        // 예시 데이터 추가
        cafeData?.let {
            addCafeData(it) }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
    private fun moveToStartActivity() {
        // 메인 액티비티로의 이동을 처리할 함수 호출
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
        finish()
    }


    fun updateMoney(amount: Int, isAddition: Boolean) {
        if (isAddition) {
            money += amount
        } else {
            money -= amount
        }
        binding?.moneysum?.text = money.toString()
    }

    fun addCafeData(data: CafeData) {
        cafeData = data
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null // 뷰 바인딩의 메모리 누수를 방지하기 위해
    }
    fun removeItem(position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            cafeDataAdapter.items.removeAt(position) // 아이템 삭제
            cafeDataAdapter.notifyItemRemoved(position) // 어댑터에 변경 사항 알림
        }
    }


}
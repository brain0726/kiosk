package com.example.kiosk

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        val takeinButton = findViewById<Button>(R.id.takein)
        takeinButton.setOnClickListener {
            moveToMainActivity()
        }
        val takeoutButton = findViewById<Button>(R.id.takeout)
        takeoutButton.setOnClickListener {
            moveToMainActivity()
        }
    }
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, StartActivity::class.java)
            context.startActivity(intent)
        }
    }

    private fun moveToMainActivity() {
        // 메인 액티비티로의 이동을 처리할 함수 호출
        MainActivity.start(this)
    }
}
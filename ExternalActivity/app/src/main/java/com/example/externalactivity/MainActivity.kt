package com.example.externalactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    companion object {
        const val ACTION_SECOND_ACTIVITY = "com.example.externalactivity.SecondActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val buttonActivity: Button = findViewById(R.id.button_activity)
        val errorTextView: TextView = findViewById(R.id.textView)

        buttonActivity.setOnClickListener {
            try {
                val intent = Intent(ACTION_SECOND_ACTIVITY)
                startActivity(intent)
            } catch (e: Exception) {
                runOnUiThread {
                    errorTextView.text = e.message
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
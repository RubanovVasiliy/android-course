package com.example.toastkotlin

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun onClick(view: View?) {
        val toast: Toast = Toast.makeText(this, R.string.learn_kotlin, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, -400)
        val toastContainer = toast.view as LinearLayout?
        val javaImageView = ImageView(applicationContext)
        javaImageView.setImageResource(R.drawable.kotlin_label)
        toastContainer!!.addView(javaImageView, 0)
        toast.show()
    }
}
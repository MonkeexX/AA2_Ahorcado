package com.example.myapplication.ui.splash

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.ui.levels.LevelSelectorActivity
import com.example.myapplication.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val boton = findViewById<Button>(R.id.button)

        boton.setOnClickListener {
            startActivity(Intent(this, LevelSelectorActivity::class.java))
        }
    }
}
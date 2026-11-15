package com.example.myapplication.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.R
import com.example.myapplication.ui.levels.LevelSelectorActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val layoutMain = findViewById<ConstraintLayout>(R.id.main)

        layoutMain.setOnClickListener {
            startActivity(
                Intent(this, LevelSelectorActivity::class.java)
            )
        }
    }
}
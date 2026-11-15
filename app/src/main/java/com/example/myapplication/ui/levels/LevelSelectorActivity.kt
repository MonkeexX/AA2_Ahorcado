package com.example.myapplication.ui.levels

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.SettingsMenu
import android.widget.Button

class LevelSelectorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_level_selector)

        val button = findViewById<Button>(R.id.buttonToolbar)

        button.setOnClickListener {
            startActivity(
                Intent(this, SettingsMenu::class.java)
            )
        }
    }
}
package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.myapplication.ui.levels.LevelSelectorActivity

class SettingsMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings_menu)

        val button = findViewById<Button>(R.id.button)
        val backButton = findViewById<Button>(R.id.buttonToolbar)

        fun startActivity(intent: Any) {}

        backButton.setOnClickListener {
            startActivity(
                finish()
            )
        }

        button.setOnClickListener{

            val currentMode = AppCompatDelegate.getDefaultNightMode()


            if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO
                )
            } else {
                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES
                )
            }

        }
    }


}
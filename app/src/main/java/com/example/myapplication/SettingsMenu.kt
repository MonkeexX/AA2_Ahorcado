package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

private const val LANG_SPANISH: String = "es"
private const val LANG_ENGLISH: String = "en"

class SettingsMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings_menu)

        val buttonColorScheme = findViewById<Button>(R.id.button)
        val backButton = findViewById<Button>(R.id.buttonToolbar)
        val buttonLanguage = findViewById<Button>(R.id.buttonLanguage);

        backButton.setOnClickListener {
           finish();
        }

        buttonColorScheme.setOnClickListener{

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

        buttonLanguage.setOnClickListener {
            val isSpanish = resources.configuration.locales[0].language == LANG_SPANISH;
            val newLanguage = if (isSpanish) LANG_ENGLISH else LANG_SPANISH;
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(newLanguage));
        }
    }


}
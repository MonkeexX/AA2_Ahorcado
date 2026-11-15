package com.example.myapplication.ui.levels

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.SettingsMenu
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Gameplay
import com.example.myapplication.base.Level

class LevelSelectorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_level_selector)

        val buttonSettings = findViewById<Button>(R.id.buttonToolbar)

        val recyclerLevels = findViewById<RecyclerView>(R.id.recyclerLevels)
        recyclerLevels.layoutManager = LinearLayoutManager(this)

        fun createLevels() = listOf(
            Level(1, getString(R.string.word_1)),
            Level(2, getString(R.string.word_2)),
            Level(3, getString(R.string.word_3)),
            Level(4, getString(R.string.word_4)),
            Level(5, getString(R.string.word_5)),
            Level(6, getString(R.string.word_6)),
            Level(7, getString(R.string.word_7)),
            Level(8, getString(R.string.word_8)),
            Level(9, getString(R.string.word_9)),
            Level(10, getString(R.string.word_10)),
            Level(11, getString(R.string.word_11)),
            Level(12, getString(R.string.word_12))
        )
        //Lambda para cuando el botón se haya pulsado
        recyclerLevels.adapter = LevelAdapter(createLevels()) { level ->
            val intent = Intent(this, Gameplay::class.java);
            intent.putExtra("WORD", level.word);
            startActivity(intent);
        }

        buttonSettings.setOnClickListener {
            startActivity(
                Intent(this, SettingsMenu::class.java)
            )
        }
    }
}
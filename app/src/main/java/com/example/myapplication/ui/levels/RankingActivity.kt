package com.example.myapplication.ui.levels

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
import android.widget.Button
import com.example.myapplication.base.ScoreData
import android.content.Intent

private const val FIRST_POSITION: Int = 1
class RankingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ranking)

        val rankingTexts = listOf(
            findViewById<TextView>(R.id.textRank1),
            findViewById<TextView>(R.id.textRank2),
            findViewById<TextView>(R.id.textRank3),
        )

        val buttonBackToLevels = findViewById<Button>(R.id.buttonBackToLevels)
        val topScores = ScoreData.getTopScores()

        for(index in rankingTexts.indices)
        {
            val score = topScores.getOrNull(index)?.toString() ?: getString(R.string.ranking_empty);
            rankingTexts[index].text = getString(R.string.ranking_entry, index + FIRST_POSITION, score);
        }

        buttonBackToLevels.setOnClickListener {
            val intent = Intent(this, LevelSelectorActivity::class.java);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
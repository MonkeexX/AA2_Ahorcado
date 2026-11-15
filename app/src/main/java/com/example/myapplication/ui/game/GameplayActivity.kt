package com.example.myapplication.ui.game

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
import com.example.myapplication.base.ScoreData
import android.content.Intent
import com.example.myapplication.SettingsMenu

class GameplayActivity : AppCompatActivity() {
    //LateInit provoca que se pueda crear una variable sin inicializar
    private lateinit var keyboard: GridLayout
    private lateinit var textWord: TextView
    private lateinit var textResult: TextView
    private lateinit var bodyParts: List<ImageView>
    private lateinit var secretWord: String
    private var levelNumber = DEFAULT_LEVEL_NUMBER
    private var errors = 0
    private var gameFinished = false
    private val clickedButton = mutableSetOf<Char>()
    //Ids de las palabras de cada nivel. Estod ebido a que la palabra no se sincronizaba,
    //ahora al cambiar de idioma la palabra se llega a sincronizar
    private val wordResourceIds = listOf(
        R.string.word_1,
        R.string.word_2,
        R.string.word_3,
        R.string.word_4,
        R.string.word_5,
        R.string.word_6,
        R.string.word_7,
        R.string.word_8,
        R.string.word_9,
        R.string.word_10,
        R.string.word_11,
        R.string.word_12
    )
    //Metodo el cual se encarga de convertir los dp a px, debido a que el LayoutParams trabaja con estas unidades
    private fun dpToPx(dp: Int): Int
    {
        return (dp * resources.displayMetrics.density).toInt()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gameplay)
        initInsets();
        bindViews();
        readIntentData();
        createKeyboard();
        setupSettingsButton();
        updateWord();
        restoreGameState(savedInstanceState);
    }
    override fun onSaveInstanceState(outState: Bundle)
    {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_LANGUAGE, currentLanguage())
        outState.putInt(STATE_ERRORS, errors)
        outState.putBoolean(STATE_GAME_FINISHED, gameFinished)
        outState.putCharArray(STATE_CLICKED_LETTERS, clickedButton.toCharArray())
    }
    private fun currentLanguage(): String
    {
        return resources.configuration.locales[0].language
    }
    private fun restoreGameState(savedInstanceState: Bundle?)
    {
        if (savedInstanceState == null || savedInstanceState.getString(STATE_LANGUAGE) != currentLanguage())
            return
        errors = savedInstanceState.getInt(STATE_ERRORS)
        savedInstanceState.getCharArray(STATE_CLICKED_LETTERS)?.let { clickedButton.addAll(it.toList()) }
        for (i in 0 until errors)
        {
            bodyParts[i].visibility = View.VISIBLE
        }
        for (i in letters.indices)
        {
            if (letters[i] in clickedButton)
            {
                keyboard.getChildAt(i).isEnabled = false
                keyboard.getChildAt(i).alpha = DISABLED_KEY_ALPHA
            }
        }
        updateWord()
        if (savedInstanceState.getBoolean(STATE_GAME_FINISHED))
            showResult(hasWon())
    }
    private fun initInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun bindViews() {
        keyboard = findViewById(R.id.keyboard);
        textWord = findViewById(R.id.textWord);
        textResult = findViewById(R.id.textResult);
        bodyParts = listOf(
            findViewById<ImageView>(R.id.imageView4),
            findViewById<ImageView>(R.id.imageView5),
            findViewById<ImageView>(R.id.imageView6),
            findViewById<ImageView>(R.id.imageView7),
            findViewById<ImageView>(R.id.imageView8),
            findViewById<ImageView>(R.id.imageView9)
        )
    }
    private fun readIntentData()
    {
        levelNumber = intent.getIntExtra(LEVEL_NUMBER, DEFAULT_LEVEL_NUMBER);
        val wordIndex = levelNumber - FIRST_LEVEL_NUMBER;
        var levelWord = "";
        if (wordIndex >= 0 && wordIndex < wordResourceIds.size)
            levelWord = getString(wordResourceIds[wordIndex]);
        else {
            val intentWord = intent.getStringExtra(WORD)
            if (intentWord != null)
                levelWord = intentWord
        }
        secretWord = levelWord.trim().uppercase()
    }
    val letters = arrayOf(
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
        'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
        'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    )
    private fun setupSettingsButton()
    {
        findViewById<Button>(R.id.buttonToolbar).setOnClickListener {
            startActivity(Intent(this, SettingsMenu::class.java))
        }
    }
    private fun createKeyboard()
    {
        val keyTypeface = ResourcesCompat.getFont(this, R.font.monogram);
        for (letter in letters)
        {
            keyboard.addView(createbutton(letter, keyTypeface))
        }
    }
    private fun createbutton(letter: Char, keyTypeface: android.graphics.Typeface?): Button
    {
        val button = Button(this)
        button.text = letter.toString()
        button.typeface = keyTypeface
        button.setTextColor(Color.BLACK)
        button.textSize = KEY_TEXT_SIZE_SP
        button.gravity = Gravity.CENTER
        //Eliminación de las transformaciones del texto
        button.isAllCaps = false
        //Fondo de los botones
        val background = GradientDrawable()
        background.setColor(Color.LTGRAY)
        background.cornerRadius = dpToPx(KEY_CORNER_RADIUS_DP).toFloat()
        button.background = background
        //Eliminación de los margenes interiores
        button.setPadding(NO_SIZE, NO_SIZE, NO_SIZE, NO_SIZE)
        button.minWidth = NO_SIZE
        button.minimumWidth = NO_SIZE
        button.minHeight = NO_SIZE
        button.minimumHeight = NO_SIZE
        //Tamaño del botón
        button.layoutParams = GridLayout.LayoutParams().apply {
            val margin = dpToPx(KEY_MARGIN_DP);
            width = NO_SIZE;
            height = dpToPx(KEY_HEIGHT_DP);
            columnSpec = GridLayout.spec(GridLayout.UNDEFINED, KEY_COLUMN_WEIGHT);
            setMargins(margin, margin, margin, margin);
        }
        button.setOnClickListener { onLetterPressed(letter, button); }
        return button;
    }
    private fun onLetterPressed(letter: Char, button: Button)
    {
        clickedButton.add(letter)
        button.isEnabled = false
        button.alpha = DISABLED_KEY_ALPHA
        if (letter in secretWord)
        {
            updateWord()
            if (hasWon())
                endGame(won = true)
        } else
            checkError()
    }
    private fun checkError()
    {
        bodyParts[errors].visibility = View.VISIBLE
        errors++
        if (errors == bodyParts.size)
            endGame(won = false)
    }
    private fun hasWon(): Boolean
    {
        for (letter in secretWord)
        {
            if (letter !in clickedButton)
                return false
        }
        return true
    }
    private fun updateWord()
    {
        val result = secretWord.map { letter ->
            if (letter in clickedButton)
                letter
            else
                HIDDEN_LETTER
        }.joinToString(LETTER_SEPARATOR)
        textWord.text = result
    }
    private fun calculateScore(): Int
    {
        return (bodyParts.size - errors) * secretWord.length
    }
    private fun endGame(won: Boolean)
    {
        if (won)
            ScoreData.addScore(levelNumber, calculateScore());
        showResult(won)
    }
    private fun showResult(won: Boolean)
    {
        gameFinished = true
        for (i in 0 until keyboard.childCount)
        {
            keyboard.getChildAt(i).isEnabled = false
        }
        if (won)
        {
            textResult.setText(R.string.game_you_win);
        } else
        {
            textWord.text = secretWord
            textResult.setText(R.string.game_you_lose);
        }
        textResult.visibility = View.VISIBLE
        textResult.setOnClickListener {
            startActivity(Intent(this, RankingActivity::class.java))
            finish()
        }
    }
    companion object
    {
        const val WORD = "WORD"
        const val LEVEL_NUMBER = "LEVEL_NUMBER"
        private const val DEFAULT_LEVEL_NUMBER = 0
        private const val FIRST_LEVEL_NUMBER = 1
        private const val HIDDEN_LETTER = '_'
        private const val LETTER_SEPARATOR = " "
        private const val KEY_TEXT_SIZE_SP = 30f
        private const val KEY_HEIGHT_DP = 48
        private const val KEY_MARGIN_DP = 3
        private const val KEY_CORNER_RADIUS_DP = 8
        private const val KEY_COLUMN_WEIGHT = 1f
        private const val DISABLED_KEY_ALPHA = 0.5f
        private const val NO_SIZE = 0
        private const val STATE_LANGUAGE = "STATE_LANGUAGE"
        private const val STATE_ERRORS = "STATE_ERRORS"
        private const val STATE_GAME_FINISHED = "STATE_GAME_FINISHED"
        private const val STATE_CLICKED_LETTERS = "STATE_CLICKED_LETTERS"
    }
}
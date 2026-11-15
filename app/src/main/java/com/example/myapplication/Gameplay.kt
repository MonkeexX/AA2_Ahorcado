
package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.graphics.Color
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import android.graphics.drawable.GradientDrawable
import com.example.myapplication.ui.levels.LevelSelectorActivity

class Gameplay : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gameplay)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val letters = arrayOf(
            'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P',
            'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L',
            'Z', 'X', 'C', 'V', 'B', 'N', 'M'
        )

        // Partes del cuerpo
        val bodyParts = listOf(
            findViewById<ImageView>(R.id.imageView4),
            findViewById<ImageView>(R.id.imageView5),
            findViewById<ImageView>(R.id.imageView6),
            findViewById<ImageView>(R.id.imageView7),
            findViewById<ImageView>(R.id.imageView8),
            findViewById<ImageView>(R.id.imageView9)
        )

        var errors = 0
        var gameFinished = false

        // Palabra secreta
        val secretWord = intent.getStringExtra("WORD")
            ?.trim()
            ?.uppercase() ?: ""

        // Letras acertadas
        val clickedButton = mutableSetOf<Char>()

        val keyboard = findViewById<GridLayout>(R.id.keyboard)
        val txtWord = findViewById<TextView>(R.id.txtWord)

        // Botón de opciones de la Toolbar
        val buttonToolbar = findViewById<Button>(R.id.buttonToolbar)

        buttonToolbar.setOnClickListener {
            finish()
        }

        // Actualizar palabra
        fun updateWord() {

            val result = secretWord.map { letter ->
                if (letter in clickedButton) {
                    letter
                } else {
                    '_'
                }
            }.joinToString(" ")

            txtWord.text = result
        }

        // Finalizar partida
        fun endGame(won: Boolean) {

            gameFinished = true

            // Desactivar todos los botones
            for (i in 0 until keyboard.childCount) {
                keyboard.getChildAt(i).isEnabled = false
            }

            if (won) {
                Toast.makeText(
                    this,
                    "¡Has ganado!",
                    Toast.LENGTH_LONG
                ).show()

            } else {
                // Mostrar la palabra correcta
                txtWord.text = secretWord

                Toast.makeText(
                    this,
                    "¡Has perdido!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        // Mostrar inicialmente los guiones
        updateWord()


// Crear teclado
        for (letter in letters) {

            val button = Button(this)

            button.text = letter.toString()

            // Fuente pixelada
            button.typeface = ResourcesCompat.getFont(this, R.font.monogram)

            // Texto visible
            button.setTextColor(Color.BLACK)
            button.textSize = 30f
            button.gravity = android.view.Gravity.CENTER
            button.textAlignment = View.TEXT_ALIGNMENT_CENTER

            // Eliminar transformaciones del texto
            button.isAllCaps = false
            button.transformationMethod = null

            // Fondo de los botones
            val background = GradientDrawable()
            background.setColor(Color.LTGRAY)
            background.cornerRadius = 8f
            button.background = background

            // Eliminar márgenes internos
            button.setPadding(0, 0, 0, 0)

            button.minWidth = 0
            button.minimumWidth = 0
            button.minHeight = 0
            button.minimumHeight = 0

            // Tamaño del botón
            val params = GridLayout.LayoutParams()

            params.width = 0
            params.height = 65

            params.columnSpec = GridLayout.spec(
                GridLayout.UNDEFINED, 1f
            )

            params.setMargins(3, 3, 3, 3)

            button.layoutParams = params

            // Acción al pulsar
            button.setOnClickListener {

                if (!gameFinished) {

                    clickedButton.add(letter)

                    if (letter in secretWord) {

                        updateWord()

                        if (secretWord.all { it in clickedButton }) {
                            endGame(true)
                        }

                    } else {

                        if (errors < bodyParts.size) {
                            bodyParts[errors].visibility = View.VISIBLE
                            errors++
                        }

                        if (errors == bodyParts.size) {
                            endGame(false)
                        }
                    }

                    button.isEnabled = false
                    button.alpha = 0.5f
                }
            }

            keyboard.addView(button)
        }
    }
}


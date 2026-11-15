package com.example.myapplication.ui.levels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.base.Level

//Le paso una variable que recibe un level y retorna un void (Unit)
class LevelAdapter(private val levels: List<Level>, private val onLevelClick: (Level) -> Unit) : RecyclerView.Adapter<LevelAdapter.LevelViewHolder>()
{
    //Como el ViewHolder solo se va a utilizar para la clase LevelAdapter lo declaro dentro de este
    //Evitando crear un script innecesario y para aclarar que esta clase forma parde del LevelAdapter y solo va a utilizarse para este
    //Además, teniendo en cuenta que es una clase que solo va a utilizarse dentro de esta, es el equivalente a una función privada
    class LevelViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val textWord : TextView = view.findViewById(R.id.textWord);
        val textLetters : TextView = view.findViewById(R.id.textLetters);
        val imageDifficulty : ImageView = view.findViewById(R.id.imageDifficulty);
        val textScore : TextView = view.findViewById(R.id.textScore);
    }
    //Metodo que se llama cuando el RecyclerView crea una fila
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder
    {
        val rowView = LayoutInflater.from(parent.context).inflate(R.layout.item_level, parent, false)
        return LevelViewHolder(rowView)
    }

    //Metodo que se llama para poner los datos dentro del ViewHolder
    override fun onBindViewHolder(holder: LevelViewHolder, position: Int)
    {
        val level = levels[position]
        holder.textWord.text = holder.itemView.context.getString(R.string.word_label, level.number, level.word)
        holder.textLetters.text = holder.itemView.context.getString(R.string.letters_label, level.word.length)
        holder.imageDifficulty.setImageResource(level.difficulty.drawable)
        holder.textScore.text = level.bestScore.toString()

        holder.itemView.setOnClickListener { onLevelClick(level) }
    }

    override fun getItemCount() = levels.size
}
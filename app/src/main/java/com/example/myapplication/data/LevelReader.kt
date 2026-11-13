package com.example.myapplication.data

import android.content.res.Resources
import com.example.myapplication.base.Level
import com.example.myapplication.R

class LevelReader
{
    fun getLevels(resources : Resources): List<Level>
    {
        val levels = mutableListOf<Level>()
        val words : Array<String> = resources.getStringArray(R.array.words);
        for((index, word) in words.withIndex())
        {
            //
            levels.add(Level(index + 1, word));
        }
        return levels;
    }
}
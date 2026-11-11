package com.example.myapplication.base

import androidx.annotation.DrawableRes
import com.example.myapplication.R

const val EASY_MIN: Int = 1;
const val EASY_MAX: Int = 4;
const val MEDIUM_MIN: Int = 5;
const val MEDIUM_MAX: Int = 7;
const val HARD_MIN: Int = 8;
private val easyRange: IntRange = EASY_MIN..EASY_MAX;
private val mediumRange: IntRange = MEDIUM_MIN..MEDIUM_MAX;
private val hardRange: IntRange = HARD_MIN..Int.MAX_VALUE;

enum class Difficulty(private val letterRange: IntRange, @DrawableRes val drawable: Int)
{
    EASY(letterRange = easyRange, drawable = R.drawable.ic_difficulty_easy),
    MEDIUM(letterRange = mediumRange, drawable = R.drawable.ic_difficulty_medium),
    HARD(letterRange = hardRange, drawable = R.drawable.ic_difficulty_hard);

    companion object
    {
        fun getDifficulty(letters: Int) :Difficulty?
        {
            for(difficulty in entries)
            {
                if(letters in difficulty.letterRange)
                    return difficulty;
            }
            return null;
        }
    }
}
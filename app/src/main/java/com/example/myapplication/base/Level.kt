package com.example.myapplication.base

import androidx.annotation.DrawableRes
import com.example.myapplication.R

enum class Difficulty(@DrawableRes val drawable: Int)
{
    EASY(drawable = R.drawable.im_difficulty_easy),
    MEDIUM(drawable = R.drawable.im_difficulty_medium),
    HARD(drawable = R.drawable.im_difficulty_hard);
}

data class Level(val number : Int, val word: String)
{
    init
    {
        require(word.isNotBlank());
    }

    val difficulty: Difficulty
    get() = when(word.length)
    {
        in 1..4 -> Difficulty.EASY;
        in 5..7 -> Difficulty.MEDIUM;
        else -> Difficulty.HARD;
    }
}

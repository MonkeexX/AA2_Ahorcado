package com.example.myapplication.base

import androidx.annotation.DrawableRes
import com.example.myapplication.R

private const val MIN_EASY: Int = 1;
private const val MAX_EASY: Int = 4;
private const val MIN_MEDIUM: Int = 5;
private const val MAX_MEDIUM: Int = 7;

enum class Difficulty(@DrawableRes val drawable: Int)
{
    EASY(drawable = R.drawable.img_difficulty_easy),
    MEDIUM(drawable = R.drawable.img_difficulty_medium),
    HARD(drawable = R.drawable.img_difficulty_hard);
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
        in MIN_EASY..MAX_EASY -> Difficulty.EASY;
        in MIN_MEDIUM..MAX_MEDIUM -> Difficulty.MEDIUM;
        else -> Difficulty.HARD;
    }
}

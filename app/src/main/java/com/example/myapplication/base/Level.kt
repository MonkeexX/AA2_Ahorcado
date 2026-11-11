package com.example.myapplication.base

data class Level(val number : Int, val word: String)
{
    init
    {
        require(word.isNotBlank());
    }
    var letters = word.length;
    val difficulty = Difficulty.getDifficulty(letters)!!;
}

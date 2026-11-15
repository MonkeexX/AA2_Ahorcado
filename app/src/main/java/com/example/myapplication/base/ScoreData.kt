package com.example.myapplication.base

private const val RANKING_SIZE: Int = 3;
private const val DEFAULT_SCORE: Int = 0;

object ScoreData
{
    private val scores: MutableMap<Int, Int> = mutableMapOf()

    fun addScore(levelNumber: Int, score: Int)
    {
        if(score > getBestScore(levelNumber))
        {
            scores[levelNumber] = score;
        }
    }

    fun getBestScore(levelNumber: Int): Int
    {
        return scores[levelNumber] ?: DEFAULT_SCORE;
    }

    fun getTopScores(): List<Int>
    {
        return scores.values.sortedDescending().take(RANKING_SIZE)
    }
}
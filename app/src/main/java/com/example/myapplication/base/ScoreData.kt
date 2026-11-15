package com.example.myapplication.base

private const val RANKING_SIZE: Int = 3;
private const val DEFAULT_SCORE: Int = 0;

object ScoreData
{
    private val bestScoresByLevel: MutableMap<Int, Int> = mutableMapOf()
    private val scores: MutableList<Int> = mutableListOf()
    fun addScore(levelNumber: Int, score: Int)
    {
        scores.add(score)
        if(score > getBestScore(levelNumber))
            bestScoresByLevel[levelNumber] = score;
    }
    fun getBestScore(levelNumber: Int): Int
    {
        return bestScoresByLevel[levelNumber] ?: DEFAULT_SCORE;
    }
    fun getTopScores(): List<Int>
    {
        return scores.sortedDescending().take(RANKING_SIZE)
    }
}
package com.arinal.made.data.model

import android.annotation.SuppressLint
import java.text.DecimalFormat
import java.text.SimpleDateFormat

data class DetailModel(
    val budget: Int,
    val genres: List<Genre>,
    val id: Int,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val vote_average: Double,
    val episode_run_time: List<Int>,
    val first_air_date: String,
    val original_name: String
) {
    data class Genre(
        val id: Int,
        val name: String
    )

    fun getTitle(category: String): String = if (category == "tv") original_name else original_title
    fun getBudget(): String = "$${DecimalFormat("#,###").format(budget)}"
    fun getRevenue(): String = "$${DecimalFormat("#,###").format(revenue)}"
    fun getGenre(): String {
        var result = ""
        for ((i, genre) in genres.withIndex()) result = result + genre.name + if (i != genres.size - 1) ", " else ""
        return result
    }

    @SuppressLint("SimpleDateFormat")
    fun getRelease(category: String): String {
        val date = if (category == "tv") first_air_date else release_date
        val convert = SimpleDateFormat("yyyy-MM-dd")
        return SimpleDateFormat("dd MMMM yyyy").format(convert.parse(date)?.time)
    }

    fun getDuration(category: String, txHour: String, txMinute: String): String {
        val duration = if (category != "tv") runtime else {
            var total = 0
            for (eps in episode_run_time)
                total += eps
            total / episode_run_time.size
        }
        val minutes = duration.rem(60)
        val hour = (duration - minutes) / 60
        val h = if (hour != 0) "$hour $txHour " else ""
        val m = "$minutes $txMinute"
        return "$h$m"
    }
}
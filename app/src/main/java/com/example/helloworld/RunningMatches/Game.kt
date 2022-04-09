package com.example.helloworld.RunningMatches

data class Game(
    val begin_at: String,
    val complete: Boolean,
    val detailed_stats: Boolean,
    val end_at: Any,
    val finished: Boolean,
    val forfeit: Boolean,
    val id: Int,
    val length: Any,
    val match_id: Int,
    val position: Int,
    val status: String,
    val video_url: Any,
    val winner: Winner,
    val winner_type: String
)
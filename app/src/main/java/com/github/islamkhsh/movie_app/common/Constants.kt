package com.github.islamkhsh.movie_app.common

object Constants {

    //numbers
    const val CACHE_SIZE = 10 * 1024 * 1024 //10MB
    const val CACHE_MAX_AGE = 30 //30 seconds
    const val CACHE_MAX_STALE = 60 * 60 * 24 * 7 //1 week

    const val ARABIC = 1
    const val ENGLISH = 0
    const val NOT_DEFINED_LANG = -1

    //time
    const val SNAK_BAR_DURATION: Int = 3 * 1000

    // paging
    const val ROWS_NUM = 20
    const val INITIAL_LOAD_SIZE_HINT = 25


    //strings
    const val NETWORKING_LOG = "networking"

    //shared preference keys
    const val PREFERENCE_NAME = "MOVIES_SHARED_PREFERENCE"
    const val CURRENT_LANGUAGE_KEY = "CURRENT_LANGUAGE"

    // database name
    const val DATABASE_NAME = "Movie_app_db"


    const val YOUTUBE_BASE_URL = "https://youtu.be/"

    // replace VIDEO_ID with video key
    const val YOUTUBE_THUMBNAIL_URL = "https://i.ytimg.com/vi/VIDEO_ID/hqdefault.jpg"
}
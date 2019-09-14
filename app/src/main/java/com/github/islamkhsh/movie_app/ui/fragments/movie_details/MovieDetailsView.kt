package com.github.islamkhsh.movie_app.ui.fragments.movie_details

import com.github.islamkhsh.movie_app.ui.base.BaseView

interface MovieDetailsView : BaseView {

    /**
     * init clicks listeners of toolbar views.
     */
    fun initMovieDetailsToolbar()

    /**
     * get all movie details
     */
    fun getMovieDetails()

    /**
     * get movie trailers
     */
    fun getMovieVideos()

    /**
     * get reviews of movie
     */
    fun getMovieReviews()

    /**
     * called when user click on review and will open its url by intent.
     * @param url String the url of the clicked review.
     */
    fun onReviewClicked(url: String)

    /**
     * called when user click on video (trailer) and will first try to play the video using
     * youtube app if failed video will play on youtube website using browser.
     * @param videoKey String the id of youtube video
     */
    fun onVideoClicked(videoKey: String)
}
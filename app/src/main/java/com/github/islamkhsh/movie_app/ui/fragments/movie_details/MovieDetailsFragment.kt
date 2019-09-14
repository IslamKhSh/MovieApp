package com.github.islamkhsh.movie_app.ui.fragments.movie_details

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.islamkhsh.movie_app.R
import com.github.islamkhsh.movie_app.common.Constants
import com.github.islamkhsh.movie_app.databinding.FragmentMovieDetailsBinding
import com.github.islamkhsh.movie_app.ui.adapters.MoviesReviewsAdapter
import com.github.islamkhsh.movie_app.ui.adapters.MoviesVideosAdapter
import com.github.islamkhsh.movie_app.ui.base.BaseFragment


class MovieDetailsFragment : MovieDetailsView,
    BaseFragment<MovieDetailsViewModel, FragmentMovieDetailsBinding>(MovieDetailsViewModel::class.java) {

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun getLayoutRes() = R.layout.fragment_movie_details
    override fun initViewModel(viewModel: MovieDetailsViewModel) {
        mBinding.viewModel = viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        viewModel.isDataLoaded.value = false
        initMovieDetailsToolbar()
        getMovieDetails()
        getMovieVideos()
        getMovieReviews()
    }

    override fun initMovieDetailsToolbar() {
        mBinding.tvToolbarMovieTitle.text = args.movieTitle

        mBinding.imgShare.setOnClickListener {
            if (viewModel.isDataLoaded.value == true){

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, viewModel.getShareMsg(context!!))
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }

    }

    override fun getMovieDetails() = viewModel.getMovieDetails(args.movieId)

    override fun getMovieVideos() {
        viewModel.getMovieVideos(args.movieId).observe(this, Observer {

            if (it.isResponseSuccessful) {
                mBinding.recyclerVideos.layoutManager = LinearLayoutManager(context)
                mBinding.recyclerVideos.adapter =
                    MoviesVideosAdapter(it.responseBody!!.results, this)
            }
        })
    }

    override fun getMovieReviews() {
        viewModel.getMovieReviews(args.movieId).observe(this, Observer {

            if (it.isResponseSuccessful) {
                mBinding.recyclerReviews.layoutManager = LinearLayoutManager(context)
                mBinding.recyclerReviews.adapter =
                    MoviesReviewsAdapter(it.responseBody!!.results, this)
            }
        })
    }

    override fun onReviewClicked(url: String) =
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))


    override fun onVideoClicked(videoKey: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoKey"))
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("${Constants.YOUTUBE_BASE_URL}$id"))

        try {
            startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            startActivity(webIntent)
        }

    }
}
package com.github.islamkhsh.movie_app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.github.islamkhsh.movie_app.R
import com.github.islamkhsh.movie_app.common.extensions.loadImg
import com.github.islamkhsh.movie_app.data.network.entities.Movie
import com.github.islamkhsh.movie_app.databinding.ItemMovieBinding
import com.github.islamkhsh.movie_app.ui.base.BasePagedListAdapter
import com.github.islamkhsh.movie_app.ui.base.BaseViewHolder
import com.github.islamkhsh.movie_app.ui.fragments.home.HomeView

class MoviesPagedAdapter(private val homeView: HomeView) : BasePagedListAdapter() {


    override fun createBinding(parent: ViewGroup, viewType: Int) = DataBindingUtil.inflate(
        LayoutInflater.from(parent.context), R.layout.item_movie, parent, false
    ) as ItemMovieBinding

    override fun bind(binding: ViewDataBinding, position: Int) {

        val movie = getItem(position) as Movie.Result

        (binding as ItemMovieBinding).imgPoster.loadImg(movie.posterPath?:"")
        binding.root.setOnClickListener { homeView.setOnMovieSelected(movie.id, movie.title) }
    }


    inner class ItemMovieViewHolder(binding: ItemMovieBinding) :
        BaseViewHolder<ItemMovieBinding>(binding)
}
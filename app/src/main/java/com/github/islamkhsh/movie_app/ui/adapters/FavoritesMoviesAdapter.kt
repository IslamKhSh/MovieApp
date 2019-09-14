package com.github.islamkhsh.movie_app.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.movie_app.R
import com.github.islamkhsh.movie_app.common.extensions.loadImg
import com.github.islamkhsh.movie_app.data.database.schema.entities.FavMovie
import com.github.islamkhsh.movie_app.data.network.entities.Movie
import com.github.islamkhsh.movie_app.data.network.entities.MovieReviews
import com.github.islamkhsh.movie_app.databinding.ItemFavoriteMovieBinding
import com.github.islamkhsh.movie_app.databinding.ItemMovieCategoryBinding
import com.github.islamkhsh.movie_app.databinding.ItemReviewBinding
import com.github.islamkhsh.movie_app.ui.base.BaseViewHolder
import com.github.islamkhsh.movie_app.ui.fragments.fav.FavoritesView
import com.github.islamkhsh.movie_app.ui.fragments.home.MovieCategory
import com.github.islamkhsh.movie_app.ui.fragments.movie_details.MovieDetailsView

class FavoritesMoviesAdapter(private val favorites : List<FavMovie>,
                             private val favoritesView: FavoritesView) :
    RecyclerView.Adapter<FavoritesMoviesAdapter.ItemFavoriteMovie>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFavoriteMovie {
        val binding : ItemFavoriteMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_favorite_movie,
            parent, false)

        return ItemFavoriteMovie(binding)
    }

    override fun getItemCount() = favorites.size

    override fun onBindViewHolder(holder: ItemFavoriteMovie, position: Int) {

        holder.binding.imgPoster.loadImg(favorites[position].poster)
        holder.binding.tvMovieTitle.text = favorites[position].title
        holder.binding.tvMovieOverview.text = favorites[position].overview

        holder.binding.root.setOnClickListener {
            favoritesView.setOnMovieSelected(favorites[position].id, favorites[position].title)
        }
    }


    inner class ItemFavoriteMovie(binding: ItemFavoriteMovieBinding) :
        BaseViewHolder<ItemFavoriteMovieBinding>(binding)
}
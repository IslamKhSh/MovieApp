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
import com.github.islamkhsh.movie_app.data.network.entities.Movie
import com.github.islamkhsh.movie_app.data.network.entities.MovieReviews
import com.github.islamkhsh.movie_app.databinding.ItemMovieCategoryBinding
import com.github.islamkhsh.movie_app.databinding.ItemReviewBinding
import com.github.islamkhsh.movie_app.ui.base.BaseViewHolder
import com.github.islamkhsh.movie_app.ui.fragments.home.MovieCategory
import com.github.islamkhsh.movie_app.ui.fragments.movie_details.MovieDetailsView

class MoviesReviewsAdapter(private val reviews : List<MovieReviews.Result>,
                           private val movieDetailsView: MovieDetailsView) :
    RecyclerView.Adapter<MoviesReviewsAdapter.ItemMovieReviewViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieReviewViewHolder {
        val binding : ItemReviewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_review,
            parent, false)

        return ItemMovieReviewViewHolder(binding)
    }

    override fun getItemCount() = reviews.size

    override fun onBindViewHolder(holder: ItemMovieReviewViewHolder, position: Int) {

        holder.binding.tvAuthorName.text = reviews[position].author
        holder.binding.tvReviewContent.text = reviews[position].content

        holder.binding.root.setOnClickListener {
            movieDetailsView.onReviewClicked(reviews[position].url)
        }
    }


    inner class ItemMovieReviewViewHolder(binding: ItemReviewBinding) :
        BaseViewHolder<ItemReviewBinding>(binding)
}
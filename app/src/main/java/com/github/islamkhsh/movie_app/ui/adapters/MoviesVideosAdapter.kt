package com.github.islamkhsh.movie_app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.movie_app.R
import com.github.islamkhsh.movie_app.common.extensions.loadThumbnail
import com.github.islamkhsh.movie_app.data.network.entities.MovieVideos
import com.github.islamkhsh.movie_app.databinding.ItemVideoBinding
import com.github.islamkhsh.movie_app.ui.base.BaseViewHolder
import com.github.islamkhsh.movie_app.ui.fragments.movie_details.MovieDetailsView

class MoviesVideosAdapter(private val videos : List<MovieVideos.Result>,
                          private val movieDetailsView: MovieDetailsView) :
    RecyclerView.Adapter<MoviesVideosAdapter.ItemMovieVideoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieVideoViewHolder {
        val binding : ItemVideoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_video,
            parent, false)

        return ItemMovieVideoViewHolder(binding)
    }

    override fun getItemCount() = videos.size

    override fun onBindViewHolder(holder: ItemMovieVideoViewHolder, position: Int) {

        holder.binding.imgVideoThumbnail.loadThumbnail(videos[position].key)

        holder.binding.root.setOnClickListener {
            movieDetailsView.onVideoClicked(videos[position].key)
        }
    }


    inner class ItemMovieVideoViewHolder(binding: ItemVideoBinding) :
        BaseViewHolder<ItemVideoBinding>(binding)
}
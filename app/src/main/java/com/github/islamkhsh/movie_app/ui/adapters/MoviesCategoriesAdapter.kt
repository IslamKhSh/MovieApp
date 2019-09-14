package com.github.islamkhsh.movie_app.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.movie_app.R
import com.github.islamkhsh.movie_app.data.network.entities.Movie
import com.github.islamkhsh.movie_app.databinding.ItemMovieCategoryBinding
import com.github.islamkhsh.movie_app.ui.base.BaseViewHolder
import com.github.islamkhsh.movie_app.ui.fragments.home.MovieCategory

class MoviesCategoriesAdapter(private val context: Context,
                              private val categories : List<MovieCategory>) :
    RecyclerView.Adapter<MoviesCategoriesAdapter.ItemMovieCategoryViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieCategoryViewHolder {
        val binding : ItemMovieCategoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_movie_category,
            parent, false)

        return ItemMovieCategoryViewHolder(binding)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: ItemMovieCategoryViewHolder, position: Int) {

        holder.binding.tvMovieCategoryName.text = context.getString(categories[position].categoryName)

        if (categories[position].isExpended)
            holder.binding.imgCategoryArrow.setImageResource(R.drawable.ic_arrow_down)
        else
            holder.binding.imgCategoryArrow.setImageResource(R.drawable.ic_arrow_right)

        holder.binding.recyclerMovies.isVisible = categories[position].isExpended

        holder.binding.recyclerMovies.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.binding.recyclerMovies.adapter = categories[position].moviesPagedAdapter

        holder.binding.containerCategoryName.setOnClickListener {
            categories[position].isExpended = !categories[position].isExpended
            notifyDataSetChanged()
        }
    }


    inner class ItemMovieCategoryViewHolder(binding: ItemMovieCategoryBinding) :
        BaseViewHolder<ItemMovieCategoryBinding>(binding)
}
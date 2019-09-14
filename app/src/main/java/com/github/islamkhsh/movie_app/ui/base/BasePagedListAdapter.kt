package com.github.islamkhsh.movie_app.ui.base


import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BasePagedListAdapter(diffCallback: DiffUtil.ItemCallback<BaseEntity> = BaseDiffCallback()) :
    PagedListAdapter<BaseEntity, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bind((holder as BaseViewHolder<*>).binding, position)
    }

    open fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        BaseViewHolder(createBinding(parent, viewType))

    abstract fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding

    protected abstract fun bind(binding: ViewDataBinding, position: Int)

    fun setList(list: PagedList<*>?) {
        submitList(list as PagedList<BaseEntity>)
    }


}
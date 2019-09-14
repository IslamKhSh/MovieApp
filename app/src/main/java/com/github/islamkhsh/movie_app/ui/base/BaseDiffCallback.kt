package com.github.islamkhsh.movie_app.ui.base

import androidx.recyclerview.widget.DiffUtil

/**
 * This diff callback informs the PagedListAdapter how to compute list differences when new
 * PagedLists arrive.
 * <p>
 * When you add a Cheese with the 'Add' button, the PagedListAdapter uses diffCallback to
 * detect there's only a single item difference from before, so it only needs to animate and
 * rebind a single view.
 *
 * @see android.support.v7.util.DiffUtil
 */
open class BaseDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem

    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}
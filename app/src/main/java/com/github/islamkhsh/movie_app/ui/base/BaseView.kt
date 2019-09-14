package com.github.islamkhsh.movie_app.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.github.islamkhsh.movie_app.data.network.entities.ApiResponse

interface BaseView {

    /**
     * use this method to provide the activity layout to be used in dataBinding
     *
     * @return the layout id
     */
    @LayoutRes
    fun getLayoutRes(): Int

    /**
     * This method is to define the life cycleOwner to the dataBinding.
     * that's because using LiveData in dataBinding requires to be aware of LifeCycle
     */
    fun initLifeCycleOwner()

    /**
     * Use this method to handle all views in your activities and fragments
     */
    fun init(savedInstanceState: Bundle?)

    /**
     * Use this method to observe for all LiveData in viewModel
     */
    fun observeLiveDatas()

}

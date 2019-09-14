package com.github.islamkhsh.movie_app.ui.fragments.fav


import android.app.AlertDialog
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.islamkhsh.movie_app.R
import com.github.islamkhsh.movie_app.data.database.schema.entities.FavMovie
import com.github.islamkhsh.movie_app.databinding.FragmentFavoritesBinding
import com.github.islamkhsh.movie_app.ui.adapters.FavoritesMoviesAdapter
import com.github.islamkhsh.movie_app.ui.base.BaseFragment

class FavoritesFragment : FavoritesView,
    BaseFragment<FavoritesViewModel, FragmentFavoritesBinding>(FavoritesViewModel::class.java) {

    override fun getLayoutRes() = R.layout.fragment_favorites
    override fun initViewModel(viewModel: FavoritesViewModel) {
        mBinding.viewModel = viewModel
    }

    override fun init(savedInstanceState: Bundle?) {
        getFavMovies()
    }


    override fun getFavMovies() {
        viewModel.getFavMovies().observe(this, Observer (::initFavMoviesRecycler))
    }

    override fun initFavMoviesRecycler(favMovies: List<FavMovie>) {

        mBinding.recyclerFavMovies.layoutManager = LinearLayoutManager(context!!)
        mBinding.recyclerFavMovies.adapter = FavoritesMoviesAdapter(favMovies, this)

        val swipeController = SwipeController(context!!, viewModel::deleteFavMovie)
        ItemTouchHelper(swipeController).attachToRecyclerView(mBinding.recyclerFavMovies)

        mBinding.recyclerFavMovies.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                swipeController.onDraw(c)
            }
        })
    }

    override fun setOnMovieSelected(movieId: Int, movieTitle: String) {
        val action =
            FavoritesFragmentDirections.actionFavoritesFragmentToMovieDetailsFragment(movieId, movieTitle)

        findNavController().navigate(action)
    }
}
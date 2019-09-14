package com.github.islamkhsh.movie_app.ui.fragments.home


import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.islamkhsh.movie_app.R
import com.github.islamkhsh.movie_app.common.Constants
import com.github.islamkhsh.movie_app.common.extensions.goToActivity
import com.github.islamkhsh.movie_app.databinding.FragmentHomeBinding
import com.github.islamkhsh.movie_app.ui.activities.SplashActivity
import com.github.islamkhsh.movie_app.ui.adapters.MoviesCategoriesAdapter
import com.github.islamkhsh.movie_app.ui.adapters.MoviesPagedAdapter
import com.github.islamkhsh.movie_app.ui.base.BaseFragment

class HomeFragment : HomeView,
    BaseFragment<HomeViewModel, FragmentHomeBinding>(HomeViewModel::class.java) {

    private val popularMoviesAdapter by lazy { MoviesPagedAdapter(this) }
    private val topRatedMoviesAdapter by lazy { MoviesPagedAdapter(this) }

    override fun getLayoutRes() = R.layout.fragment_home
    override fun initViewModel(viewModel: HomeViewModel) {
        mBinding.viewModel = viewModel
    }


    override fun init(savedInstanceState: Bundle?) {
        initHomeToolbar()
        initCategories()
        getPopularMovies(savedInstanceState)
        getTopRatedMovies(savedInstanceState)
    }


    override fun initHomeToolbar() {
        mBinding.tvChangeLang.setOnClickListener {
            viewModel.changeLang()
            goToActivity(SplashActivity::class.java)
            activity?.finish()
        }

        mBinding.imgFav.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favoritesFragment)
        }
    }

    override fun initCategories() {

        if (viewModel.categories.isNullOrEmpty())
            viewModel.categories = listOf(
                MovieCategory(R.string.popular, popularMoviesAdapter),
                MovieCategory(R.string.top_rated, topRatedMoviesAdapter)
            )

        mBinding.recyclerCategories.layoutManager = LinearLayoutManager(context)
        mBinding.recyclerCategories.adapter =
            MoviesCategoriesAdapter(context!!, viewModel.categories!!)
    }

    override fun getPopularMovies(savedInstanceState: Bundle?) {

        if (popularMoviesAdapter.itemCount != 0) return

        viewModel.getPopularMoviesList().observe(this, Observer {
            popularMoviesAdapter.setList(it)
        })

        viewModel.getPopularMoviesApiResponse()
    }

    override fun getTopRatedMovies(savedInstanceState: Bundle?) {

        if (topRatedMoviesAdapter.itemCount != 0) return

        viewModel.getTopRatedMoviesList().observe(this, Observer {
            topRatedMoviesAdapter.setList(it)
        })

        viewModel.getTopRatedMoviesApiResponse()
    }

    override fun setOnMovieSelected(movieId: Int, movieTitle: String) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(movieId, movieTitle)

        findNavController().navigate(action)
    }
}
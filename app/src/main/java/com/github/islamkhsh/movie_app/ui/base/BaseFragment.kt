package com.github.islamkhsh.movie_app.ui.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.github.islamkhsh.movie_app.R
import com.github.islamkhsh.movie_app.common.extensions.errorMsg
import com.github.islamkhsh.movie_app.common.extensions.logE
import com.github.islamkhsh.movie_app.data.network.entities.ApiResponse
import com.github.islamkhsh.movie_app.data.network.entities.ErrorResponse
import com.google.gson.Gson
import java.io.IOException

abstract class BaseFragment<VM : BaseViewModel,
        DB : ViewDataBinding>(private val mViewModelClass: Class<VM>) : Fragment(), BaseView {

    lateinit var viewModel: VM
    open lateinit var mBinding: DB

    private fun initDataBinding(inflater: LayoutInflater, container: ViewGroup) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
    }

    private fun getViewM(): VM = ViewModelProviders.of(activity!!).get(mViewModelClass)

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewM()
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        container?.let {
            initDataBinding(inflater, it)
        }

        initLifeCycleOwner()
        initViewModel(viewModel)

        super.onCreateView(inflater, container, savedInstanceState)

        return mBinding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveDatas()
        init(savedInstanceState)

    }

    @CallSuper
    override fun initLifeCycleOwner() {
        mBinding.lifecycleOwner = this
    }

    @CallSuper
    override fun observeLiveDatas() {

        viewModel.errorResponse.observe(this, Observer(::handleErrorResponse))

        viewModel.isUpBtnClicked.observe(this, Observer {

            if (it){
                viewModel.isUpBtnClicked.value = false
                findNavController().navigateUp()
            }
        })
    }

    /**
     *  You need to override this method.
     *  And you need to set viewModel to mBinding: mBinding.viewModel = viewModel
     *
     *  @param viewModel the instance of ViewModel that is related to the  activity
     */
    abstract fun initViewModel(viewModel: VM)

    /**
     * use this method to handle error and throwable during networking
     *
     * Errors:
     * code(500) -> server error
     * code(504) -> no internet connection
     * other -> try convert errorBody to ErrorResponse and show message,
     * if exception show something error
     *
     * Throwables:
     * throwable is IOException -> error in connection
     * other -> something error
     */
    private fun handleErrorResponse(response: ApiResponse<*>) {

        if (response.exception != null) {

            if (response.isCanceled) return

            logE(response.exception!!)

            when (response.exception) {
                is IOException -> errorMsg(R.string.msg_error_connection)
                else -> errorMsg(R.string.msg_something_error)

            }

        } else if (!response.isResponseSuccessful) {

            when (response.responseCode) {
                500 -> errorMsg(R.string.msg_server_error)
                504 -> errorMsg(R.string.no_internet)
                else -> {
                    try {
                        val error = Gson().fromJson<ErrorResponse>(
                            response.errorBody?.string(),
                            ErrorResponse::class.java
                        )
                        errorMsg(error.message)
                    } catch (e: Exception) {
                        errorMsg(R.string.msg_something_error)
                    }
                }
            }
        }
    }

}

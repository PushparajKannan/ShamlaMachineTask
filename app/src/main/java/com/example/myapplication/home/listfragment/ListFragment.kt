package com.example.myapplication.home.listfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.home.listfragment.adapter.UserListAdapter
import com.example.myapplication.network.AuthState
import com.example.myapplication.network.RestClient
import com.example.myapplication.repository.ApiDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListFragment : Fragment() {
    private lateinit var mBinding: FragmentListBinding


    private val viewModel: ListFragmentViewModel by lazy {
        val networkRepo = ApiDataRepository(RestClient.getInstance().getApiService())
        val factory = ListFragmentViewModel.Factory(
            networkRepo
        )
        ViewModelProvider(this, factory).get(ListFragmentViewModel::class.java)
    }

    private val userListAdapter: UserListAdapter by lazy {
        UserListAdapter { _model ->
            val action = ListFragmentDirections.actionListFragmentToDetailedFragment(_model)
            requireView().findNavController().navigate(
                action,
                NavOptions.Builder().setLaunchSingleTop(true).build()
            )
        }
    }


    companion object {
        @JvmStatic
        @BindingAdapter("internetImage")
        open fun internetImage(view: ImageView, imageUrl: String?) {
            if (imageUrl != null)
                Glide.with(view.context).load(imageUrl)
                    .apply(RequestOptions().optionalCenterCrop()).into(
                        view
                    )


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        mBinding.viewModel = viewModel
        mBinding.lifecycleOwner = viewLifecycleOwner
       // userListAdapter.setHasStableIds(true)
        mBinding.rvList.adapter = userListAdapter
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userListFromNetworkObserver()
    }

    private fun userListFromNetworkObserver() {
        GlobalScope.launch(Dispatchers.IO) {
            viewModel.userList.collect {
                when (it) {
                    is AuthState.Loading -> {
                        withContext(Dispatchers.Main) {
                            viewModel.isUserDataFetching.value = true
                        }

                    }
                    is AuthState.Authenticated -> {
                        withContext(Dispatchers.Main) {
                            viewModel.isUserDataFetching.value = false
                            userListAdapter.updateList(it.data)
                        }
                    }
                    is AuthState.AuthenticationFailed -> {
                        withContext(Dispatchers.Main) {
                            viewModel.isUserDataFetching.value = false
                        }
                    }

                }
            }
        }
    }
}
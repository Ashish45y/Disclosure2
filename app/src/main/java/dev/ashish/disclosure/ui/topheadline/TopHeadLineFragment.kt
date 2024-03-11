package dev.ashish.disclosure.ui.topheadline

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.ashish.disclosure.data.model.Article
import dev.ashish.disclosure.databinding.FragmentTopHeadLineBinding
import dev.ashish.disclosure.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TopHeadLineFragment : Fragment() {

    lateinit var newsListViewModel: TopHeadlineViewModel

    @Inject
    lateinit var adapter: TopHeadlineAdapter

    private lateinit var topHeadLineBinding: FragmentTopHeadLineBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        topHeadLineBinding = FragmentTopHeadLineBinding.inflate(layoutInflater, container, false)
        setupViewModel()
        setupUI()
        setupObserver()
        return topHeadLineBinding.root

    }

    private fun setupViewModel() {
        newsListViewModel = ViewModelProvider(this)[TopHeadlineViewModel::class.java]
    }

    private fun setupUI() {
        val recyclerView = topHeadLineBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsListViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            topHeadLineBinding.progressBar.visibility = View.GONE
                            renderList(it.data)
                            topHeadLineBinding.recyclerView.visibility = View.VISIBLE
                            adapter.setData(it.data)
                        }

                        is UiState.Loading -> {
                            topHeadLineBinding.progressBar.visibility = View.VISIBLE
                            topHeadLineBinding.recyclerView.visibility = View.GONE
                        }

                        is UiState.Error -> {
                            //Handle Error
                            topHeadLineBinding.progressBar.visibility = View.GONE
                            // Toast.makeText(this@TopHeadlineActivi, it.message, Toast.LENGTH_LONG)
                            // .show()
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(articleList: List<Article>) {
        adapter.addData(articleList)
        adapter.notifyDataSetChanged()
    }

}
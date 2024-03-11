package dev.ashish.disclosure.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import dev.ashish.disclosure.data.model.SearchResponse
import dev.ashish.disclosure.databinding.FragmentSearchBinding
import dev.ashish.disclosure.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var searchAdapter: SearchAdapter

    lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        setupViewModel()
        setupUI()
        setupObserver()
        return binding.root

    }

    private fun setupViewModel() {
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.search.visibility = View.VISIBLE
                            renderList(it.data)
                            binding.rvSearch.visibility = View.VISIBLE
                            searchAdapter.setData(it.data)
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun setupUI() {
        val recyclerView = binding.rvSearch
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = searchAdapter

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length!! >= 2) {
                    // just hit the api here and call the function where you can update the updater
                    searchViewModel.performSearch(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {
                return
            }

        })

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(articleList: List<SearchResponse.Article>) {
        searchAdapter.addData(articleList)
        searchAdapter.notifyDataSetChanged()
    }
}
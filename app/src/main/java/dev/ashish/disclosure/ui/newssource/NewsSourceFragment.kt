package dev.ashish.disclosure.ui.newssource

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ashish.disclosure.DisclosureApplication
import dev.ashish.disclosure.data.model.NewsSources
import dev.ashish.disclosure.databinding.FragmentNewsSourceBinding
import dev.ashish.disclosure.di.component.DaggerFragmentComponent
import dev.ashish.disclosure.di.module.FragmentModule
import dev.ashish.disclosure.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsSourceFragment : Fragment() {
    private lateinit var newsSource: FragmentNewsSourceBinding

    @Inject
    lateinit var newsSourceViewModel: NewsSourceViewModel

    @Inject
    lateinit var newsSourceAdaptor: NewsSourceAdaptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        newsSource = FragmentNewsSourceBinding.inflate(layoutInflater, container, false)
        injectDependencies()
        setupUI()
        setupObserver()
        return newsSource.root
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsSourceViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            newsSource.progressBar.visibility = View.GONE
                            renderList(it.data)
                            newsSource.recyclerView.visibility = View.VISIBLE
                            newsSourceAdaptor.setData(it.data)
                        }

                        is UiState.Loading -> {
                            newsSource.progressBar.visibility = View.VISIBLE
                            newsSource.recyclerView.visibility = View.GONE
                        }

                        is UiState.Error -> {
                            newsSource.progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun injectDependencies() {
        DaggerFragmentComponent
            .builder()
            .applicationComponent((requireContext().applicationContext as DisclosureApplication).applicationComponent)
            .fragmentModule(FragmentModule(this))
            .build()
            .inject(this)
    }

    private fun setupUI() {
        val recyclerView = newsSource.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = newsSourceAdaptor
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(newsSourceList: List<NewsSources>) {
        newsSourceAdaptor.addData(newsSourceList)
        newsSourceAdaptor.notifyDataSetChanged()
    }
}
package dev.ashish.disclosure.ui.language

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import dev.ashish.disclosure.databinding.FragmentLanguageBinding
import dev.ashish.disclosure.di.component.DaggerFragmentComponent
import dev.ashish.disclosure.di.module.FragmentModule
import dev.ashish.disclosure.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject


class LanguageFragment : Fragment() {
    private lateinit var languageFragment: FragmentLanguageBinding

    @Inject
    lateinit var adapter: LanguageAdapter

    @Inject
    lateinit var languageViewModel: LanguageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        languageFragment = FragmentLanguageBinding.inflate(layoutInflater, container, false)
        injectDependencies()
        setupUI()
        setupObserver()
        return languageFragment.root
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                languageViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            renderList(it.data)
                            languageFragment.rvlang.visibility = View.VISIBLE
                            adapter.setData(it.data)
                        }

                        else -> {
                            Log.d("LanguageList", "setupObserver: ")
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
        val recyclerView = languageFragment.rvlang
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(newsSourceList: List<NewsSources>) {
        adapter.addData(newsSourceList)
        adapter.notifyDataSetChanged()
    }
}
package dev.ashish.disclosure.ui.country

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
import dev.ashish.disclosure.databinding.FragmentCountryBinding
import dev.ashish.disclosure.di.component.DaggerFragmentComponent
import dev.ashish.disclosure.di.module.FragmentModule
import dev.ashish.disclosure.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryFragment : Fragment() {
    lateinit var binding : FragmentCountryBinding
    @Inject
    lateinit var adapter: CountryAdapter
    @Inject
    lateinit var viewModel: CountryViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =  FragmentCountryBinding.inflate(layoutInflater,container,false)
        injectDependencies()
        setupUI()
        setupObserver()
        return binding.root
    }
    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            renderList(it.data)
                            binding.rvCountry.visibility = View.VISIBLE
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
        val recyclerView = binding.rvCountry
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
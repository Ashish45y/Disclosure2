package dev.ashish.disclosure.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.ashish.disclosure.R
import dev.ashish.disclosure.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
   private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.top.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_topHeadLineFragment)
        }
        binding.newsource.setOnClickListener {
           // Log.d("Ashish", "onViewCreated: ${Gson().toJson()}")
            findNavController().navigate(R.id.action_homeFragment_to_newsSourceFragment)
        }
        binding.language.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_languageFragment)
        }
        binding.country.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_countryFragment)
        }
        binding.search.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }
}
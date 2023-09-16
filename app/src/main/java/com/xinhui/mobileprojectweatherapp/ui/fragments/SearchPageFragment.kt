package com.xinhui.mobileprojectweatherapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.xinhui.mobileprojectweatherapp.R
import com.xinhui.mobileprojectweatherapp.databinding.FragmentSearchPageBinding
import com.xinhui.mobileprojectweatherapp.ui.util.Constant.TAG
import com.xinhui.mobileprojectweatherapp.ui.viewModels.SearchPageViewModel

class SearchPageFragment : Fragment() {
    private lateinit var binding: FragmentSearchPageBinding
    private val viewModel: SearchPageViewModel by viewModels{
        SearchPageViewModel.Factory
    }
    private lateinit var navController: NavController
    private val cities = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = NavHostFragment.findNavController(this)

        val file = resources.openRawResource(R.raw.cities)
        file.bufferedReader().useLines {
            it.forEach { c ->
                cities.add(c)
            }
        }
        val adapter = ArrayAdapter(
            requireContext(),
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            cities
        )

        binding.run {
            autocomplete.setAdapter(adapter)
            autocomplete.addTextChangedListener {
                Log.d(TAG, "onViewCreated: ${it.toString()}")
            }

            ivEdit.setOnClickListener {
                val action = SearchPageFragmentDirections.actionSearchPageToEditDelete()
                navController.navigate(action)
            }
        }
    }

}
package com.xinhui.mobileprojectweatherapp.ui.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.xinhui.mobileprojectweatherapp.R
import com.xinhui.mobileprojectweatherapp.databinding.FragmentSearchPageBinding
import com.xinhui.mobileprojectweatherapp.ui.adapters.SearchPageAdapter
import com.xinhui.mobileprojectweatherapp.ui.viewModels.SearchPageViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class SearchPageFragment : Fragment() {

    private lateinit var binding: FragmentSearchPageBinding
    private val viewModel: SearchPageViewModel by viewModels{
        SearchPageViewModel.Factory
    }
    private lateinit var navController: NavController
    private val cities = mutableListOf<String>()
    private lateinit var adapter:SearchPageAdapter
    private var selectedCity = ""

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
        val citiesAdapter = ArrayAdapter(
            requireContext(),
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            cities
        )

        setupAdapter()

        lifecycleScope.launch{
            viewModel.getLocations().collect{
                adapter
                    .setLocation(it)
            }
        }

        binding.run {
            searchPageVM = viewModel
            lifecycleOwner = viewLifecycleOwner

            autocomplete.setAdapter(citiesAdapter)

            autocomplete.setOnItemClickListener { _, _, i, _ ->
                selectedCity = autocomplete.adapter.getItem(i).toString()
                val action = SearchPageFragmentDirections
                    .actionSearchPageToSavedLocationWeather(selectedCity,-1)
                navController.navigate(action)
                autocomplete.setText("")
            }

            ivSearch.setOnClickListener {
                if (autocomplete.text.toString().isEmpty()){
                    Snackbar.make(view,
                        "search a city...",
                        Snackbar.LENGTH_LONG)
                        .show()
                }
                else{
                    selectedCity = autocomplete.text.replace("\\s".toRegex(), "")
                    if (!cities.contains(selectedCity)) {
                        Snackbar.make(view,
                            "city not found, try with another city",
                            Snackbar.LENGTH_LONG).show()
                    }
                    else{
                        val action = SearchPageFragmentDirections
                            .actionSearchPageToSavedLocationWeather(selectedCity,-1)
                        navController.navigate(action)
                    }
                }
            }

            ivEdit.setOnClickListener {
                val action = SearchPageFragmentDirections.actionSearchPageToEditDelete()
                navController.navigate(action)
            }
        }
    }

    fun setupAdapter(){
        adapter = SearchPageAdapter(emptyList()){
            val action = SearchPageFragmentDirections.actionSearchPageToSavedLocationWeather(it.city,it.id!!)
            navController.navigate(action)
        }

        binding.rvSearchPage.adapter = adapter
        binding.rvSearchPage.layoutManager = LinearLayoutManager(requireContext())
    }

}
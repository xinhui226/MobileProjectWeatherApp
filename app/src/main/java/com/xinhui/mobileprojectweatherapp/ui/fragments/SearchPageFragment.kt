package com.xinhui.mobileprojectweatherapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.xinhui.mobileprojectweatherapp.R
import com.xinhui.mobileprojectweatherapp.databinding.FragmentSearchPageBinding
import com.xinhui.mobileprojectweatherapp.ui.adapters.SearchPageAdapter
import com.xinhui.mobileprojectweatherapp.ui.viewModels.SearchPageViewModel
import kotlinx.coroutines.launch

class SearchPageFragment : Fragment() {

    val viewModel: SearchPageViewModel by viewModels {
        SearchPageViewModel.Factory
    }

    private lateinit var binding: FragmentSearchPageBinding
    private lateinit var adapter: SearchPageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            searchPageVM = viewModel
            lifecycleOwner = lifecycleOwner
        }

        binding.ivHamburger.setOnClickListener {
            showPopupMenu(it)
        }

        setupAdapter()

        lifecycleScope.launch{
            viewModel.getLocations().collect{
                adapter
                    .setLocation(it)
            }
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.menuInflater.inflate(R.menu.pop_up_menu, popupMenu.menu)
        popupMenu.setForceShowIcon(true)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.editList -> {
                    Toast.makeText(requireContext(), it.title, Toast.LENGTH_LONG).show()
                }
                R.id.celcius -> {
                    Toast.makeText(requireContext(), it.title, Toast.LENGTH_LONG).show()
                }
                R.id.fahrenheit -> {
                    Toast.makeText(requireContext(), it.title, Toast.LENGTH_LONG).show()
                }
            }
            return@setOnMenuItemClickListener true
        }
    }

    fun setupAdapter(){
        adapter = SearchPageAdapter(emptyList())

        binding.rvSearchPage.adapter = adapter
        binding.rvSearchPage.layoutManager = LinearLayoutManager(requireContext())
    }

}
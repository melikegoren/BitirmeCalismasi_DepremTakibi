package com.example.depremtakibi.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.depremtakibi.R

import com.example.depremtakibi.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), OnHomeClickListener {

    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var earthquakeAdapter: HomeAdapter
    //private val mapperImpl = HomeListMapperImpl()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("homeFragment", "inside onViewCreated, before observing")
        searchView()
        observeViewModel()
        menuItemClick()


    }

    private fun observeViewModel() {
        viewModel.earthquakeUiState.observe(viewLifecycleOwner) {
            when (it) {
                is HomeUiState.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is HomeUiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is HomeUiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    earthquakeAdapter = HomeAdapter(it.data as ArrayList<HomeUiData>, this)
                    setRecyclerView(binding.recyclerView, it.data, earthquakeAdapter)


                    binding.swipeRef.setOnRefreshListener {
                        setRecyclerView(binding.recyclerView, it.data, earthquakeAdapter)
                        binding.swipeRef.isRefreshing = false
                    }
                    chipGroup(it.data)
                }
            }
        }
    }


    private fun searchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                earthquakeAdapter.getFilter().filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                earthquakeAdapter.getFilter().filter(newText)
                return true
            }

        })


    }

    private fun setRecyclerView(
        recyclerView: RecyclerView,
        list: ArrayList<HomeUiData>,
        adapter: HomeAdapter,
    ) {
        recyclerView.layoutManager =
            LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    private fun chipGroup(data: ArrayList<HomeUiData>) {
        var adapter: HomeAdapter?
        binding.apply {
            chip4.setOnClickListener {
                val filteredList = data.filter { it.magnitude <= 4 }
                adapter = HomeAdapter(filteredList as ArrayList<HomeUiData>, this@HomeFragment)
                if (filteredList.isEmpty()) {
                    binding.imageView.visibility = View.VISIBLE
                    binding.textView.visibility = View.VISIBLE
                    binding.recyclerView.adapter = null
                } else {
                    setRecyclerView(recyclerView, filteredList, adapter!!)
                    binding.imageView.visibility = View.INVISIBLE
                    binding.textView.visibility = View.INVISIBLE
                }
            }

            chip54.setOnClickListener {
                val filteredList = data.filter { it.magnitude > 4 && it.magnitude <= 5 }
                adapter = HomeAdapter(filteredList as ArrayList<HomeUiData>, this@HomeFragment)
                if (filteredList.isEmpty()) {
                    binding.imageView.visibility = View.VISIBLE
                    binding.textView.visibility = View.VISIBLE
                    binding.recyclerView.adapter = null
                } else {
                    setRecyclerView(recyclerView, filteredList, adapter!!)
                    binding.imageView.visibility = View.INVISIBLE
                    binding.textView.visibility = View.INVISIBLE

                }
            }

            chip65.setOnClickListener {
                val filteredList = data.filter { it.magnitude > 5 && it.magnitude <= 6 }
                adapter = HomeAdapter(filteredList as ArrayList<HomeUiData>, this@HomeFragment)
                if (filteredList.isEmpty()) {
                    binding.imageView.visibility = View.VISIBLE
                    binding.textView.visibility = View.VISIBLE
                    binding.recyclerView.adapter = null
                } else {
                    setRecyclerView(recyclerView, filteredList, adapter!!)
                    binding.imageView.visibility = View.INVISIBLE
                    binding.textView.visibility = View.INVISIBLE
                }

            }

            chip76.setOnClickListener {
                val filteredList = data.filter { it.magnitude > 6 && it.magnitude <= 7 }
                adapter = HomeAdapter(filteredList as ArrayList<HomeUiData>, this@HomeFragment)
                if (filteredList.isEmpty()) {
                    binding.imageView.visibility = View.VISIBLE
                    binding.textView.visibility = View.VISIBLE
                    binding.recyclerView.adapter = null
                } else {
                    setRecyclerView(recyclerView, filteredList, adapter!!)
                    binding.imageView.visibility = View.INVISIBLE
                    binding.textView.visibility = View.INVISIBLE
                }
            }

            chip7.setOnClickListener {
                val filteredList = data.filter { it.magnitude > 7 }
                adapter = HomeAdapter(filteredList as ArrayList<HomeUiData>, this@HomeFragment)
                if (filteredList.isEmpty()) {
                    binding.imageView.visibility = View.VISIBLE
                    binding.textView.visibility = View.VISIBLE
                    binding.recyclerView.adapter = null
                } else {
                    setRecyclerView(recyclerView, filteredList, adapter!!)
                    binding.imageView.visibility = View.INVISIBLE
                    binding.textView.visibility = View.INVISIBLE
                }
            }
        }
    }


    private fun menuItemClick(): Boolean {
        binding.apply {
            homeToolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.info -> {
                        val action = HomeFragmentDirections.actionHomeFragmentToInfoFragment()
                        findNavController().navigate(action)
                        return@setOnMenuItemClickListener true
                    }
                    else -> {
                        return@setOnMenuItemClickListener false
                    }
                }
            }
        }
        return true

    }

    override fun onCardViewClick(id: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
        action.id = id
        findNavController().navigate(action)
    }
}


interface OnHomeClickListener {
    fun onCardViewClick(id: String)
}
package com.example.depremtakibi.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.depremtakibi.R
import com.example.depremtakibi.databinding.FragmentDetailBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    val binding get() = _binding!!


    private val args: DetailFragmentArgs by navArgs()

    private val viewModel by viewModels<DetailViewModel>()

    private lateinit var cityAdapter: ClosestCitiesAdapter

    private lateinit var airportAdapter: AirportsAdapter

    private var earthquakeData: DetailsUiData? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(layoutInflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressed()
        getDetails()
        navigateBack()

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.onResume()
    }


    private fun observeViewModel() {
        viewModel.detailUiState.observe(viewLifecycleOwner) {
            when (it) {
                is DetailsUiState.Loading -> {
                    whenLoading()
                }

                is DetailsUiState.Error -> {

                    Toast.makeText(requireContext(),
                        getString(R.string.unknown_error),
                        Toast.LENGTH_SHORT).show()
                }

                is DetailsUiState.Success -> {

                    uploadData(it.data)
                    cityAdapter = ClosestCitiesAdapter(it.data.closestCities)
                    airportAdapter = AirportsAdapter(it.data.airports)
                    setRecyclerViewForClosestCities(binding.rvClosestCities, cityAdapter)
                    setRecyclerViewForAirports(binding.rvAirports, airportAdapter)
                    earthquakeData = it.data

                    var googleMap: GoogleMap?

                    val latitude = it.data.geojson.coordinates.get(1)
                    val longitude = it.data.geojson.coordinates.get(0)


                    binding.mapView.getMapAsync {

                        googleMap = it

                        val markerOptions = MarkerOptions().position(LatLng(latitude, longitude))

                        googleMap!!.addMarker(markerOptions)

                        val cameraPosition =
                            CameraPosition.Builder().target(LatLng(latitude, longitude)).zoom(10f)
                                .build()
                        googleMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(
                            cameraPosition))
                    }
                }
            }

        }
    }

    private fun setRecyclerViewForClosestCities(
        recyclerView: RecyclerView,
        adapter: ClosestCitiesAdapter,
    ) {
        recyclerView.layoutManager =
            LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    private fun setRecyclerViewForAirports(recyclerView: RecyclerView, adapter: AirportsAdapter) {

        recyclerView.layoutManager =
            LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }


    private fun getDetails() {
        val id = args.id
        observeViewModel()
        viewModel.getEartquakeById(id)
    }

    @SuppressLint("SetTextI18n")
    private fun uploadData(detailUiData: DetailsUiData) {

        binding.apply {
            tvLoc.text = detailUiData.title
            tvPopulation.text = getString(R.string.population) + detailUiData.population.toString()

        }
    }

    private fun navigateBack() {
        binding.detailToolbar.setNavigationOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToHomeFragment()
            findNavController().navigate(action)
        }
    }


    private fun onBackPressed() =
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }

    private fun whenLoading() {
        binding.rvClosestCities.adapter = null
        binding.rvAirports.adapter = null
        binding.tvLoc.text = null
        binding.tvPopulation.text = null
    }


}
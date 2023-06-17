package com.example.depremtakibi.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.depremtakibi.data.dto.Airport
import com.example.depremtakibi.databinding.AirportItemBinding

class AirportsAdapter(private val airportList: List<Airport?>?) :
    RecyclerView.Adapter<AirportsAdapter.AirportViewHolder>() {

    class AirportViewHolder(val binding: AirportItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirportViewHolder {
        val binding = AirportItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AirportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AirportViewHolder, position: Int) {
        val airport = airportList?.get(position)


        holder.binding.apply {
            tvAirport.text = airport?.name
        }
    }

    override fun getItemCount(): Int = airportList?.size!!


}
package com.example.depremtakibi.presentation.detail

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.depremtakibi.data.dto.ClosestCity
import com.example.depremtakibi.databinding.ClosestCityItemBinding

class ClosestCitiesAdapter(private val cityList: List<ClosestCity?>?) :
    RecyclerView.Adapter<ClosestCitiesAdapter.ClosestCityViewHolder>() {

    class ClosestCityViewHolder(val binding: ClosestCityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClosestCityViewHolder {
        val binding =
            ClosestCityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClosestCityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClosestCityViewHolder, position: Int) {
        val earthquakeCity = cityList?.get(position)
        Log.d("eartCitySize", earthquakeCity.toString())

        holder.binding.apply {
            tvCity.text = earthquakeCity?.name
        }
    }

    override fun getItemCount(): Int = cityList?.size!!


}
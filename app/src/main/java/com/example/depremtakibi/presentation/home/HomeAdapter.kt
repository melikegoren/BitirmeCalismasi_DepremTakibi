package com.example.depremtakibi.presentation.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.example.depremtakibi.databinding.EarthquakeItemBinding
import java.util.*
import kotlin.collections.ArrayList

class HomeAdapter(
    private val earthquakeList: ArrayList<HomeUiData>?,
    private val onHomeClickListener: OnHomeClickListener,
) :
    RecyclerView.Adapter<HomeAdapter.HomeUiModelViewHolder>() {

    private val initialEarthquakeList = ArrayList<HomeUiData>().apply {
        earthquakeList?.let { addAll(it) }
    }

    class HomeUiModelViewHolder(val binding: EarthquakeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onBindViewHolder(holder: HomeUiModelViewHolder, position: Int) {
        val earthquake = earthquakeList?.get(position)
        Log.d("adapter", "onBindViewHolder")

        holder.binding.apply {
            tvTitle.text = earthquake?.title.toString()
            tvTime.text = earthquake?.time.toString()
            tvMag.text = earthquake?.magnitude.toString()

            cardview.setOnClickListener {
                earthquake?.eartquake_id.let { id ->
                    if (id != null) {
                        onHomeClickListener.onCardViewClick(id)
                        Log.d("idd", id)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeUiModelViewHolder {
        val binding =
            EarthquakeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeUiModelViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return earthquakeList?.size ?: 0
    }


    private val filter = object : Filter() {
        override fun performFiltering(p0: CharSequence?): FilterResults {
            val filteredList: ArrayList<HomeUiData> = ArrayList()
            if (p0.isNullOrEmpty()) {
                initialEarthquakeList.let {
                    filteredList.addAll(it)
                }
            } else {
                val query = p0.toString().trim().lowercase()
                initialEarthquakeList.forEach {
                    if (it.cityName.lowercase(Locale.ROOT).contains(query)) {
                        filteredList.add(it)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            if (p1?.values is ArrayList<*>) {
                earthquakeList?.clear()
                earthquakeList?.addAll(p1.values as ArrayList<HomeUiData>)
                notifyDataSetChanged()
            }
        }

    }

    fun getFilter(): Filter {
        return filter
    }


}
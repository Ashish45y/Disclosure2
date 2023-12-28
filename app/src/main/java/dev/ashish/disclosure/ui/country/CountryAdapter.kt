package dev.ashish.disclosure.ui.country

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.ashish.disclosure.data.model.NewsSources
import dev.ashish.disclosure.databinding.CountyLayoutBinding

class CountryAdapter(private val countryList: MutableList<NewsSources> = mutableListOf()) :
    RecyclerView.Adapter<CountryAdapter.DataViewHolder>() {
    class DataViewHolder(private val binding: CountyLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(list: NewsSources) {
            binding.ctCountry.text = list.country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            CountyLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(countryList[position])

    override fun getItemCount(): Int = countryList.size


    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<NewsSources>) {
        countryList.clear()
        countryList.addAll(list)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newSourceList: List<NewsSources>) {
        countryList.clear()
        countryList.addAll(newSourceList)
        notifyDataSetChanged()
    }
}
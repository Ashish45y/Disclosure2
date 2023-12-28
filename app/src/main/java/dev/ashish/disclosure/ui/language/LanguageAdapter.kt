package dev.ashish.disclosure.ui.language

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.ashish.disclosure.data.model.NewsSources
import dev.ashish.disclosure.databinding.LangLayoutBinding

class LanguageAdapter(private val languageList: MutableList<NewsSources> = mutableListOf()) :
    RecyclerView.Adapter<LanguageAdapter.DataViewHolder>() {
    class DataViewHolder(private val binding: LangLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(list: NewsSources) {
            binding.tLang.text =list.language
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LangLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(languageList[position])

    override fun getItemCount(): Int = languageList.size


    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<NewsSources>) {
        languageList.clear()
        languageList.addAll(list)
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(newSourceList: List<NewsSources>) {
        languageList.clear()
        languageList.addAll(newSourceList)
        notifyDataSetChanged()
    }
}


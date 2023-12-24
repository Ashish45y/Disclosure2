package dev.ashish.disclosure.ui.newssource

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import dev.ashish.disclosure.data.model.NewsSources
import dev.ashish.disclosure.databinding.NewsSourcesBinding

class NewsSourceAdaptor(private val sourceList: MutableList<NewsSources> = mutableListOf()) :
    RecyclerView.Adapter<NewsSourceAdaptor.DataViewHolder>()
{
    class DataViewHolder(private val binding: NewsSourcesBinding) :
        RecyclerView.ViewHolder(binding.root) {
          fun bind(sourceList: NewsSources) {
             binding.textViewName.text = sourceList.name
              binding.textViewDes.text = sourceList.description
              itemView.setOnClickListener {
                  val builder = CustomTabsIntent.Builder()
                  val customTabsIntent = builder.build()
                  customTabsIntent.launchUrl(it.context, Uri.parse(sourceList.url))
              }
              binding.textViewCat.text = sourceList.category
              binding.textViewLang.text = sourceList.language
              binding.textViewCount.text = sourceList.country
          }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            NewsSourcesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(sourceList[position])
    }
    override fun getItemCount(): Int {
        return sourceList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<NewsSources>) {
        sourceList.clear()
        sourceList.addAll(list)
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(newSourceList: List<NewsSources>) {
        sourceList.clear()
        sourceList.addAll(newSourceList)
        notifyDataSetChanged()
    }
}

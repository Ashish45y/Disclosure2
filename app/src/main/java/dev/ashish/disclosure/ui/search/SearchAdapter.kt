package dev.ashish.disclosure.ui.search

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.ashish.disclosure.data.model.Article
import dev.ashish.disclosure.data.model.SearchResponse
import dev.ashish.disclosure.databinding.NewsSourcesBinding
import dev.ashish.disclosure.databinding.SearchLayoutBinding
import dev.ashish.disclosure.databinding.TopHeadLineBinding
import dev.ashish.disclosure.ui.newssource.NewsSourceAdaptor
import dev.ashish.disclosure.ui.topheadline.TopHeadlineAdapter

class SearchAdapter(private val article: MutableList<SearchResponse.Article> = mutableListOf()) :
    RecyclerView.Adapter<SearchAdapter.DataViewHolder>() {
    class DataViewHolder(private val binding: SearchLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(searchList: SearchResponse.Article) {
            binding.textViewTitle.text = searchList.title
            binding.textViewDescription.text = searchList.description
            binding.textViewSource.text = searchList.source?.name
            Glide.with(binding.imageViewBanner.context)
                .load(searchList.urlToImage)
                .into(binding.imageViewBanner)
            itemView.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(it.context, Uri.parse(searchList.url))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            SearchLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = article.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(article[position])

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<SearchResponse.Article>) {
        article.clear()
        article.addAll(list)
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(newArticles: List<SearchResponse.Article>) {
        article.clear()
        article.addAll(newArticles)
        notifyDataSetChanged()
    }
}


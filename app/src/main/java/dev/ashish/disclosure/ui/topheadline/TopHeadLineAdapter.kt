package dev.ashish.disclosure.ui.topheadline

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.ashish.disclosure.data.model.Article
import dev.ashish.disclosure.databinding.TopHeadLineBinding

class TopHeadlineAdapter(private val articleList: MutableList<Article> = mutableListOf()) : RecyclerView.Adapter<TopHeadlineAdapter.DataViewHolder>() {
    class DataViewHolder(private val binding: TopHeadLineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description
            binding.textViewSource.text = article.source.name
            Glide.with(binding.imageViewBanner.context)
                .load(article.imageUrl)
                .into(binding.imageViewBanner)
            itemView.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(it.context, Uri.parse(article.url))
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            TopHeadLineBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(articleList[position])

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<Article>) {
        articleList.clear()
        articleList.addAll(list)
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(newArticles: List<Article>) {
        articleList.clear()
        articleList.addAll(newArticles)
        notifyDataSetChanged()
    }
}
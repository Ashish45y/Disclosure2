package dev.ashish.disclosure.data.model.newssource

import com.google.gson.annotations.SerializedName
import dev.ashish.disclosure.data.local.entity.NewsSources

data class APINewsSource(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("url") val url: String = "",
    @SerializedName("category") val category: String = "",
    @SerializedName("language") val language: String = "",
    @SerializedName("country") val country: String = "",
)

fun APINewsSource.asSource() = NewsSources(
    sourceId = id ?: "",
    name = name,
    description = description,
    url = url,
    category = category,
    language = language,
    country = country
)

package dev.ashish.disclosure.data.model.newssource

import com.google.gson.annotations.SerializedName

data class NewsSourcesResponse(
    @SerializedName("status") val status: String = "",
    @SerializedName("sources") val newsSource: List<APINewsSource> = arrayListOf(),
)

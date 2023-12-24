package dev.ashish.disclosure.data.model

import com.google.gson.annotations.SerializedName

data class NewsSourcesResponse(
    @SerializedName("sources") var sources: ArrayList<NewsSources> = arrayListOf()
)

package dev.ashish.disclosure.data.model.topheadlines

import com.google.gson.annotations.SerializedName
import dev.ashish.disclosure.data.local.entity.Source

data class ApiSource(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String = "",
)

fun ApiSource.toSourceEntity(): Source {
    return Source(id, name)
}

package fr.ydelerm.bankintest.model

import com.google.gson.annotations.SerializedName

data class ResourceResult (
    @SerializedName("resources") val resources : List<Resource>
)
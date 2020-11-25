package fr.ydelerm.bankintest.model

import com.google.gson.annotations.SerializedName

data class CategoryResult (
    @SerializedName("resources") val categories : List<Category>
)
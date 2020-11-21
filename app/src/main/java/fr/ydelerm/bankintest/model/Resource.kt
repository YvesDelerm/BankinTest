package fr.ydelerm.bankintest.model

import com.google.gson.annotations.SerializedName

data class Resource (

    @SerializedName("id") val id : Int,
    @SerializedName("resource_uri") val resourceUri : String,
    @SerializedName("resource_type") val resourceType : String,
    @SerializedName("name") val name : String,
    @SerializedName("parent") val parent : Parent,
    @SerializedName("custom") val custom : Boolean,
    @SerializedName("other") val other : Boolean,
    @SerializedName("is_deleted") val isDeleted : Boolean
)
package fr.ydelerm.bankintest.model

import com.google.gson.annotations.SerializedName

data class Parent (

    @SerializedName("id") val id : Int,
    @SerializedName("resource_uri") val resourceUri : String,
    @SerializedName("resource_type") val resourceType : String
)
package fr.ydelerm.bankintest.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName


data class Parent (

    @ColumnInfo(name = "parentId") @SerializedName("id") val id : Int,
    @ColumnInfo(name = "parentResourceUri") @SerializedName("resource_uri") val resourceUri : String,
    @ColumnInfo(name = "parentResourceType") @SerializedName("resource_type") val resourceType : String
)
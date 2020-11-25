package fr.ydelerm.bankintest.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Category (

    @PrimaryKey
    @SerializedName("id") val id : Int,
    @SerializedName("resource_uri") val resourceUri : String,
    @SerializedName("resource_type") val resourceType : String,
    @SerializedName("name") val name : String,
    @Embedded @SerializedName("parent") val parent : Parent?,
    @SerializedName("custom") val custom : Boolean,
    @SerializedName("other") val other : Boolean,
    @SerializedName("is_deleted") val isDeleted : Boolean
)
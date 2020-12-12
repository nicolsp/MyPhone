package com.example.myphone.pojo


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "details_table")
data class detailsItem(
    @SerializedName("credit")
    val credit: Boolean?,
    @SerializedName("description")
    val description: String?,
    @PrimaryKey
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("lastPrice")
    val lastPrice: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: Int?
)
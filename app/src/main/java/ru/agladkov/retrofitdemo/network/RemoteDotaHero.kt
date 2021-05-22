package ru.agladkov.retrofitdemo.network

import com.google.gson.annotations.SerializedName

data class RemoteDotaHero(
    val id: Int,
    val name: String,
    @SerializedName("localized_name") val localizedName: String,
    @SerializedName("primary_attr") val attribute: String,
    @SerializedName("attack_type") val attackType: String,
    val roles: List<String>
)
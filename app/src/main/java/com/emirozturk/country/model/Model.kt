package com.emirozturk.country.model
import com.google.gson.annotations.SerializedName

data class Country(
    val name: String?,
    val capital: String?,
    val region: String?,
    val currency: String?,
    @SerializedName("flag") // Api'den bu isimle gelecek veri
    val url: String?,
    val language: String?)
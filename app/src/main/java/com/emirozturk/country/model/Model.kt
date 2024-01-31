package com.emirozturk.country.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Country(
    @ColumnInfo("name") // Boş bırakırsak değişkenin adını kullanıp tablo colonu oluşturur.
    val name: String?,

    @ColumnInfo("capital")
    val capital: String?,

    @ColumnInfo("region")
    val region: String?,

    @ColumnInfo("currency")
    val currency: String?,

    @ColumnInfo("flag")
    @SerializedName("flag") // Api'den bu isimle gelecek veri
    val url: String?,

    @ColumnInfo("language")
    val language: String?
) {
    @PrimaryKey(true)
    var id : Int = 0
}
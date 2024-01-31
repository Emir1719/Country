package com.emirozturk.country.service
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emirozturk.country.model.Country

// Dao: Data access object
@Dao
interface CountryDao {
    @Insert
    suspend fun insertAll(vararg countries : Country) : ArrayList<Long>

    @Query("select * from country")
    suspend fun getAllCountry(): ArrayList<Country>

    @Query("select * from country where id = :id")
    suspend fun getCountry(id: String): Country

    @Query("delete from country")
    suspend fun deleteAllCountry(): Boolean
}
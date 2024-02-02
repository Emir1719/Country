package com.emirozturk.country.service
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emirozturk.country.model.Country

// Dao: Data access object
@Dao
interface CountryDao {
    @Insert
    suspend fun insertAll(vararg countries : Country) : List<Long>

    @Query("select * from Country")
    fun getAllCountry(): LiveData<List<Country>>

    @Query("select * from Country where id = :id")
    suspend fun getCountry(id: Int): Country

    @Query("delete from Country")
    suspend fun deleteAllCountry()
}
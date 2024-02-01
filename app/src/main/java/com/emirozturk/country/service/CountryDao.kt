package com.emirozturk.country.service
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emirozturk.country.model.Country

// Dao: Data access object
@Dao
interface CountryDao {
    @Insert
    suspend fun insertAll(vararg countries : Country) : List<Long>

    @Query("select * from country")
     fun getAllCountry(): LiveData<List<Country>>

    @Query("select * from country where id = :id")
    suspend fun getCountry(id: String): Country

    @Query("delete from country")
    suspend fun deleteAllCountry()
}
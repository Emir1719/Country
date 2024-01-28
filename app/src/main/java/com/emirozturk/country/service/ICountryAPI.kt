package com.emirozturk.country.service
import com.emirozturk.country.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface ICountryAPI {
    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries(): Single<ArrayList<Country>>
}
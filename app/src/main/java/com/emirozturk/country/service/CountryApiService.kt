package com.emirozturk.country.service
import com.emirozturk.country.model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryApiService {
    private val baseUrl = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ICountryAPI::class.java)

    fun getData(): Single<ArrayList<Country>> {
        return api.getCountries()
    }
}
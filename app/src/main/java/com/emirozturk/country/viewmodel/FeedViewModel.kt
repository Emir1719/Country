package com.emirozturk.country.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emirozturk.country.model.Country

class FeedViewModel: ViewModel() {
    val countries = MutableLiveData<ArrayList<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refleshData() {
        val country1 = Country("f", "c","c","c","c","c");
        val country2 = Country("f", "c","c","c","c","c");
        val country3 = Country("f", "c","c","c","c","c");

        val list = arrayListOf<Country>(country1, country2, country3)
        countries.value = list
        countryError.value = false
        countryLoading.value = false
    }
}
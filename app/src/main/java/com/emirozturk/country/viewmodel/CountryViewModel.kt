package com.emirozturk.country.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emirozturk.country.model.Country

class CountryViewModel: ViewModel() {
    val country = MutableLiveData<Country>()

    fun getDataFromRoom() {
        val country = Country("d","","","","","")
        this.country.value = country
    }
}
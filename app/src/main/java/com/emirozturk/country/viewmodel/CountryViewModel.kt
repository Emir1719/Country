package com.emirozturk.country.viewmodel
import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emirozturk.country.model.Country
import com.emirozturk.country.service.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel(app: Application): BaseViewModel(app) {
    val country = MutableLiveData<Country>()

    fun getDataFromRoom(id: Int) {
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            country.value = dao.getCountry(id)
        }
    }
}
package com.emirozturk.country.viewmodel
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emirozturk.country.model.Country
import com.emirozturk.country.service.CountryApiService
import com.emirozturk.country.service.CountryDatabase
import com.emirozturk.country.util.AppSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(app: Application): BaseViewModel(app) {
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()
    private val api = CountryApiService()
    private val disposable = CompositeDisposable()
    private val sharedPreferences = AppSharedPreferences(getApplication())
    private val refleshTime = 10 * 60 * 1000 * 1000 * 1000L //10 dakika

    fun refleshData() {
        val updateTime = sharedPreferences.getTime()
        if (updateTime != null && updateTime != -1L && (System.nanoTime()-updateTime)<refleshTime) {
            getDataFromSQLite()
        }
        else {
            getDataFromApi()
        }
    }

    fun refleshDataFromApi() {
        getDataFromApi()
    }

    private fun getDataFromApi() {
        countryLoading.value = true
        disposable.add(
            api.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ArrayList<Country>>(){
                    override fun onSuccess(t: ArrayList<Country>) {
                        storeInSQLite(t)
                        Toast.makeText(getApplication(), "From Api", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun getDataFromSQLite() {
        launch {
            val countries = CountryDatabase(getApplication()).countryDao().getAllCountry()
            showCountry(countries.value!!)
            Toast.makeText(getApplication(), "From SQLite", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showCountry(countries: List<Country>) {
        this.countries.value = countries
        countryLoading.value = false
        countryError.value = false
    }

    private fun storeInSQLite(list: ArrayList<Country>) {
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountry()
            val listLong = dao.insertAll(*list.toTypedArray()) //listedeki elemanları tek tek atar
            var i = 0
            while (i < list.size) {
                list[i].id = listLong[i].toInt()
                i++
            }
            showCountry(list)
        }
        sharedPreferences.saveTime(System.nanoTime())
    }
}
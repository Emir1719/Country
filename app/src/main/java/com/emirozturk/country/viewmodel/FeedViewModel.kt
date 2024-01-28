package com.emirozturk.country.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emirozturk.country.model.Country
import com.emirozturk.country.service.CountryApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel: ViewModel() {
    val countries = MutableLiveData<ArrayList<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()
    private val api = CountryApiService();
    private val disposable = CompositeDisposable()

    fun refleshData() {
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
                        countries.value = t
                        countryLoading.value = false
                        countryError.value = false
                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                        e.printStackTrace()
                    }
                })
        )
    }
}
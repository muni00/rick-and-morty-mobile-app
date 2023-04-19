package com.muazzeznihalbahadir.invio_case.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muazzeznihalbahadir.invio_case.api.Repository
import com.muazzeznihalbahadir.invio_case.model.KarakterList
import com.muazzeznihalbahadir.invio_case.model.Lokasyon
import com.muazzeznihalbahadir.invio_case.model.LokasyonList
import com.muazzeznihalbahadir.invio_case.model.Page
import kotlinx.coroutines.launch
import retrofit2.Response

class SharedViewModel(val repository: Repository ) : ViewModel(){
    var listKarakterler = MutableLiveData<Response<KarakterList>>()
    var listLokasyonlar = MutableLiveData<Response<LokasyonList>>()
    var lokasyonCurrentResult = MutableLiveData<List<Lokasyon>>()

    private val _showLoading = MutableLiveData<Boolean>()
    private val _showLoadingLocation = MutableLiveData<Boolean>()

    val showLoading: LiveData<Boolean>
        get() = _showLoading

    val showLoadingLocation: LiveData<Boolean>
        get() = _showLoadingLocation

    fun getCharacters(page:Int){

        viewModelScope.launch {
            _showLoading.value = true
            try {
                val karakterler = repository.getCharacters(page)
                listKarakterler.value = karakterler
            }catch (e:Exception){
                println(e.message)
            }
            _showLoading.value = false
        }
    }

    fun getLocations(page:Int){
        viewModelScope.launch {
            _showLoading.value = true
           // _showLoadingLocation.value = true
            try {
                val lokasyonlar = repository.getLocations(page)
                listLokasyonlar.value = lokasyonlar
                lokasyonCurrentResult.value = lokasyonlar.body()!!.results
                println(lokasyonCurrentResult.value!!.size)
            }catch (e:Exception){
                println(e.message)
            }
            _showLoading.value = false
          //  _showLoadingLocation.value = false
        }
    }

    fun getLocationsNew(page:Int){
        viewModelScope.launch {
            _showLoadingLocation.value = true
            try {
                val lokasyonlar = repository.getLocations(page)
                val lokasyonResult = lokasyonlar.body()!!.results
                lokasyonCurrentResult.value = lokasyonCurrentResult.value?.plus(lokasyonResult)
                val lokasyon =
                    lokasyonCurrentResult.value?.let { LokasyonList(lokasyonlar.body()!!.info, it) }
                listLokasyonlar.value = Response.success(lokasyon)
            }catch (e:Exception){
                println(e.message)
            }
            _showLoadingLocation.value = false
        }
    }

    fun getMultipleCharacters(ids:String){
        viewModelScope.launch {
            _showLoading.value = true
            try {
                val karakterler = repository.getMultipleCharacters(ids)
                val multipleCharaterResult = KarakterList(karakterler.body()!!)
                listKarakterler.value = Response.success(multipleCharaterResult)
            }catch (e:Exception){
                println(e.message)
            }
            _showLoading.value = false
        }
    }
}
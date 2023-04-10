package com.muazzeznihalbahadir.invio_case.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muazzeznihalbahadir.invio_case.api.Repository
import com.muazzeznihalbahadir.invio_case.model.KarakterList
import com.muazzeznihalbahadir.invio_case.model.LokasyonList
import kotlinx.coroutines.launch
import retrofit2.Response

class SharedViewModel(val repository: Repository ) : ViewModel(){
    var listKarakterler = MutableLiveData<Response<KarakterList>>()
    var listLokasyonlar = MutableLiveData<Response<LokasyonList>>()

    fun getCharacters(page:Int){
        viewModelScope.launch {
            val karakterler = repository.getCharacters(page)
            listKarakterler.value = karakterler
        }
    }

    fun getLocations(page:Int){
        viewModelScope.launch {
            val lokasyonlar = repository.getLocations(page)
            listLokasyonlar.value = lokasyonlar
        }
    }

    fun getMultipleCharacters(ids:String){
        viewModelScope.launch {
            val karakterler = repository.getMultipleCharacters(ids)
            val multipleCharaterResult = KarakterList(karakterler.body()!!)
            listKarakterler.value = Response.success(multipleCharaterResult)
        }
    }
}
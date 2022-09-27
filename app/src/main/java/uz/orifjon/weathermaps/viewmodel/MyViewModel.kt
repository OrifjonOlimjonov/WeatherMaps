package uz.orifjon.weathermaps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.orifjon.weathermaps.models.WeatherInformation

class MyViewModel : ViewModel() {

    private var liveData: MutableLiveData<WeatherInformation> = MutableLiveData()

    fun getWeather() {
        // ApiClient.getRetrofit().create(ApiService::class.java).getWeather()
    }


    fun getValue(): LiveData<WeatherInformation> {
        return liveData
    }

    fun setValue(weather:WeatherInformation){
        liveData.postValue(weather)
    }

}
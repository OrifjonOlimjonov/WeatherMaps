package uz.orifjon.weathermaps.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.orifjon.weathermaps.database.Coordinate
import uz.orifjon.weathermaps.databinding.FragmentInfoBinding
import uz.orifjon.weathermaps.models.WeatherInformation
import uz.orifjon.weathermaps.retrofit.ApiClient
import uz.orifjon.weathermaps.retrofit.ApiService
import uz.orifjon.weathermaps.viewmodel.MyViewModel

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        val weather = arguments?.getSerializable("weather") as Coordinate
        viewModel.getValue().observe(viewLifecycleOwner) {
            binding.tv.text = it.name + (it.main.temp - 273) + "°"
        }
        // val weather = Coordinate(0,40.0,60.0)
        ApiClient.getRetrofit().create(ApiService::class.java)
            .getWeather(weather.latitude, weather.longitude, "bb216539a8644925f2471a06841392f4")
            .enqueue(object : Callback<WeatherInformation> {
                override fun onResponse(
                    call: Call<WeatherInformation>,
                    response: Response<WeatherInformation>
                ) {
                    if (response.isSuccessful) {
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.tv.visibility = View.VISIBLE
                       // binding.tv.text = response.body()!!.name + " " + (response.body()!!.main.temp - 273)+ "°"
                        val temperature = (response.body()!!.main.temp - 273).toString().substring(0,2)
                        val info = "Hudud: ${response.body()!!.name}\nHarorat: ${temperature}°\nShamol tezligi: ${response.body()!!.wind.speed} m/s\nBosim: ${response.body()!!.main.pressure} P\nMaksimal harorat: ${(response.body()!!.main.temp_max - 273).toString().substring(0,5)}°\nMinimal harorat: ${(response.body()!!.main.temp_min - 273).toString().substring(0,5)}°\nTavsif: ${response.body()!!.weather[0].description}\nNamlik: ${response.body()!!.main.humidity}%"
                        binding.tv.text = info
                    }
                }

                override fun onFailure(call: Call<WeatherInformation>, t: Throwable) {

                }

            })


        return binding.root
    }


}
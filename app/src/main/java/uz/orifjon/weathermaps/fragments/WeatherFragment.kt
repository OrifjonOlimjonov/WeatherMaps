package uz.orifjon.weathermaps.fragments

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import uz.orifjon.weathermaps.R
import uz.orifjon.weathermaps.database.Coordinate
import uz.orifjon.weathermaps.database.CoordinateDatabase
import uz.orifjon.weathermaps.databinding.FragmentWeatherBinding


class WeatherFragment : Fragment() {
    private lateinit var map: GoogleMap

    private var locationSaved: LatLng? = null
    private val callback = OnMapReadyCallback { googleMap ->


        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(41.311081, 69.240562), 4.0f))

        googleMap.setOnMapClickListener {
            val location = LatLng(it.latitude, it.longitude)
            locationSaved = it

            googleMap.addMarker(MarkerOptions().position(location).title("Xalqlar do'stligi"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
            binding.getWeather.visibility = View.VISIBLE
        }

        googleMap.setOnMapLongClickListener {
//            if (locationSaved != null) CoordinateDatabase.getDatabase(requireContext())
//                .coordinateDao().add(
//                Coordinate(
//                    0,
//                    locationSaved!!.latitude,
//                    locationSaved!!.longitude
//                )
//            )
//            val location = LatLng(it.latitude, it.longitude)
//            googleMap.addMarker(MarkerOptions().position(location).title("Xalqlar do'stligi"))
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        }

        googleMap.setOnMarkerClickListener {

            Toast.makeText(
                requireContext(),
                "${it.position.latitude} + ${it.position.longitude} + ${it.title}",
                Toast.LENGTH_SHORT
            ).show()
            true
        }

        map = googleMap
    }


    private lateinit var binding: FragmentWeatherBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)

        binding.getWeather.setOnClickListener {
            if (locationSaved != null) {
                val bundle = Bundle()
                val location = Coordinate(0, locationSaved!!.latitude, locationSaved!!.longitude)
                bundle.putSerializable("weather", location)
                findNavController().navigate(R.id.infoFragment, bundle)
            } else {
                Toast.makeText(requireContext(), "Koordinata belgilamadingiz!!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onStop() {
        super.onStop()

        map.clear()
    }


}
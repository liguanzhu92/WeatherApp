package com.example.weatherapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.ViewModelFactory
import com.example.weatherapp.WeatherApp
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.utils.ItemClickListener
import javax.inject.Inject

class MainFragment : Fragment() {

    companion object {
        const val TAG = "MainFragment.class"
        const val CITY_LATITUDE = "city_latitude"
        const val CITY_LONGITUDE = "city_longitude"
        fun newInstance() = MainFragment()
        fun newInstance(lat: Double, lon: Double) = MainFragment().apply {
            arguments = Bundle().apply {
                putDouble(CITY_LATITUDE, lat)
                putDouble(CITY_LONGITUDE, lon)
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    private val viewModel: MainViewModel by lazy {
        viewModelFactory.get<MainViewModel>(
            requireActivity()
        )
    }
    private lateinit var cityAdapter: CityListAdapter
    private lateinit var editText: EditText
    private lateinit var recyclerView: RecyclerView
    private var hasLocationAccess = false

    override fun onCreate(savedInstanceState: Bundle?) {
        WeatherApp.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(CITY_LATITUDE) && it.containsKey(CITY_LONGITUDE)) {
                val lat = it.getDouble(CITY_LATITUDE)
                val lon = it.getDouble(CITY_LONGITUDE)
                hasLocationAccess = true
                viewModel.getWeather(lat, lon)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(layoutInflater, container, false)
       cityAdapter = CityListAdapter().apply {
            setOnClickListener(object: ItemClickListener{
                override fun onItemClick(lat: Double, lon: Double) {
                    viewModel.getWeather(lat, lon)
                    viewModel.clearList()
                    recyclerView.visibility = View.GONE
                }
            })
        }
        editText = binding.mainCityEdit
        if (editText.text.toString().isBlank()) {
            editText.setText(viewModel.getLastCity())
        }
        recyclerView = binding.mainRecyclerList.apply {
            setHasFixedSize(true)
            adapter = cityAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
        binding.mainSearchBtn.setOnClickListener {
            viewModel.getCityList(editText.text.toString())
            viewModel.updateLastCity(editText.text.toString())
        }
        viewModel.cityListLivedata.observe(viewLifecycleOwner) {
            recyclerView.visibility = View.VISIBLE
            cityAdapter.refreshCityList(it)
        }
        viewModel.weatherLivedata.observe(viewLifecycleOwner) {
            binding.city = it.name
            binding.weather = it.weather[0]
            binding.temperature = it.tempInfo
            Glide.with(requireContext())
                .load(viewModel.getWeatherIcon(it.weather[0]))
                .centerCrop()
                .into(binding.weatherImg)
        }
        return binding.root
    }

}
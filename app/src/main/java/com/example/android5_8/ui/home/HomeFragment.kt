package com.example.android5_8.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android5_8.R
import com.example.android5_8.WeatherViewModel
import com.example.android5_8.databinding.FragmentHomeBinding
import com.example.android5_8.ui.home.bottomSheet.BottomSheetListener
import com.example.android5_8.ui.home.bottomSheet.ModalBottomSheetDialog
import com.example.android5_8.ui.search.SearchFragment
import com.example.android5_8.utils.GpsTracker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), BottomSheetListener {
    private var gpsTracker: GpsTracker? = null
    private lateinit var binding: FragmentHomeBinding
    private var callback: BottomSheetListener? = null
    private val viewModel: WeatherViewModel by activityViewModels()
    lateinit var modal: ModalBottomSheetDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        callback = this
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        modal = callback?.let { ModalBottomSheetDialog(it) }!!
        modal?.isCancelable = false
        requireActivity().supportFragmentManager.let { modal?.show(it, ModalBottomSheetDialog.TAG) }
        observe()
        getLocation()
    }

    private fun observe() {
        viewModel.weatherLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                with(binding) {
                    tvCity.text = it.city.name
                    tvDesc.text =
                        it.list[0].weather[0].description.replaceFirstChar(Char::titlecase)
                    tvHigh.text = "H:${String.format("%.0f", it.list[0].main.temp_max)}°"
                    tvLow.text = "L:${String.format("%.0f", it.list[0].main.temp_min)}°"
                    tvTemp.text = "${String.format("%.0f", it.list[0].main.temp)}°"
                }
            }


        }
    }

    private fun getLocation() {
        gpsTracker = GpsTracker(requireContext())
        if (gpsTracker!!.canGetLocation()) {
            val latitude: Double = gpsTracker!!.getLatitude()
            val longitude: Double = gpsTracker!!.getLongitude()
            viewModel.getWeather(String.format("%.2f", latitude), String.format("%.2f", longitude))
        } else {
            gpsTracker!!.showSettingsAlert()
        }
    }

    override fun onDestroyView() {
        val rootView = view?.rootView
        if (rootView?.parent != null) {
            (rootView.parent as ViewGroup).endViewTransition(rootView)
        }
        super.onDestroyView()
    }

    override fun onBottomSheetNavigationPress() {
        modal.dismiss()
        findNavController().navigate(R.id.action_navigation_home_to_searchFragment)
    }
}
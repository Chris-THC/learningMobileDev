package com.cth.horoscopoapp.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.cth.horoscopoapp.databinding.FragmentHoroscopeBinding
import com.cth.horoscopoapp.domain.model.HoroscopeInfo.Aquarius
import com.cth.horoscopoapp.domain.model.HoroscopeInfo.Aries
import com.cth.horoscopoapp.domain.model.HoroscopeInfo.Cancer
import com.cth.horoscopoapp.domain.model.HoroscopeInfo.Capricorn
import com.cth.horoscopoapp.domain.model.HoroscopeInfo.Gemini
import com.cth.horoscopoapp.domain.model.HoroscopeInfo.Leo
import com.cth.horoscopoapp.domain.model.HoroscopeInfo.Libra
import com.cth.horoscopoapp.domain.model.HoroscopeInfo.Pisces
import com.cth.horoscopoapp.domain.model.HoroscopeInfo.Sagittarius
import com.cth.horoscopoapp.domain.model.HoroscopeInfo.Scorpio
import com.cth.horoscopoapp.domain.model.HoroscopeInfo.Taurus
import com.cth.horoscopoapp.domain.model.HoroscopeInfo.Virgo
import com.cth.horoscopoapp.domain.model.HoroscopeModel
import com.cth.horoscopoapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {
    // usando el view model
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    // hacer funcionar el rv
    private lateinit var adapterHoroscope: HoroscopeAdapter

    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        initList()
        initUIState()
    }

    private fun initList() {
        adapterHoroscope = HoroscopeAdapter(onItemSelected = {

            val typeHoroscope = when (it) {
                Aquarius -> HoroscopeModel.Aquarius
                Aries -> HoroscopeModel.Aries
                Cancer -> HoroscopeModel.Cancer
                Capricorn -> HoroscopeModel.Capricorn
                Gemini -> HoroscopeModel.Gemini
                Leo -> HoroscopeModel.Leo
                Libra -> HoroscopeModel.Libra
                Pisces -> HoroscopeModel.Pisces
                Sagittarius -> HoroscopeModel.Sagittarius
                Scorpio -> HoroscopeModel.Scorpio
                Taurus -> HoroscopeModel.Taurus
                Virgo -> HoroscopeModel.Virgo
            }

            findNavController().navigate(
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(
                    typeHoroscope
                )
            )
        })

        binding.rvHoroscope.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = adapterHoroscope
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeViewModel.horoscope.collect {
                    adapterHoroscope.updateInfo(it)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
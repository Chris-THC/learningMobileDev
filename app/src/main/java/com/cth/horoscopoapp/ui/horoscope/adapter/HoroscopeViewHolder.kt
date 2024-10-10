package com.cth.horoscopoapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.cth.horoscopoapp.databinding.ItemHoroscopeBinding
import com.cth.horoscopoapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view)

    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.tvTitle.text = binding.tvTitle.context.getString(horoscopeInfo.name)

        binding.parent.setOnClickListener {
            rotateAnimation(
                binding.ivHoroscope,
                newLambdaFunctionByInfoSelected = { onItemSelected(horoscopeInfo) })
        }
    }

    private fun rotateAnimation(view: View, newLambdaFunctionByInfoSelected: () -> Unit) {
        view.animate().apply {
            duration = 400
            interpolator = LinearInterpolator()
            rotationBy(360f)
            withEndAction {
                newLambdaFunctionByInfoSelected()
            }
            start()
        }
    }
}
package ru.barinov.drivershelper.UI

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import ru.barinov.drivershelper.databinding.HomeFragmentLayoutBinding

class StatisticFragment: Fragment() {

    private lateinit var binding: HomeFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
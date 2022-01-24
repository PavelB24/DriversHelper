package ru.barinov.drivershelper.UI

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import ru.barinov.drivershelper.R
import ru.barinov.drivershelper.databinding.HomeFragmentLayoutBinding

class StatisticFragment: Fragment() {

    private lateinit var binding: HomeFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentLayoutBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (!menu.hasVisibleItems()) {
            inflater.inflate(R.menu.navigation_menu, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }
}
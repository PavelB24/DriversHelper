package ru.barinov.drivershelper.UI

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import ru.barinov.drivershelper.R
import ru.barinov.drivershelper.databinding.HomeFragmentLayoutBinding

class StatisticFragment: Fragment() {

    private lateinit var binding: HomeFragmentLayoutBinding
    private lateinit var pieChart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentLayoutBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initViews()


        val colorArray = mutableListOf<Int>(Color.BLACK, Color.RED, Color.GRAY)
        val pieDataSet = PieDataSet(dataValues(), "")
        pieDataSet.colors = colorArray
        val pieData = PieData(pieDataSet)
        pieData.setValueTextSize(20F)

        pieChart = binding.pieChart
        pieChart.data = pieData
        pieChart.setUsePercentValues(true)
        pieChart.setDrawEntryLabels(false)


        super.onViewCreated(view, savedInstanceState)

    }

    private fun initViews() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (!menu.hasVisibleItems()) {
            inflater.inflate(R.menu.statistic_fragmet_appbar_menu, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun dataValues(): ArrayList<PieEntry> {
        return arrayListOf(PieEntry(15F, "Hello"), PieEntry(20F, "Hey"), PieEntry(30F, "OOO"))
    }
}
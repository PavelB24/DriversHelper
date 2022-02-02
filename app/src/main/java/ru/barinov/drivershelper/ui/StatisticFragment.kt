package ru.barinov.drivershelper.ui

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.google.android.material.bottomsheet.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import ru.barinov.drivershelper.R
import ru.barinov.drivershelper.databinding.*
import ru.barinov.drivershelper.ui.recyclerViews.*

class StatisticFragment: Fragment() {

    private lateinit var binding: HomeFragmentLayoutBinding
    private lateinit var pieChart: PieChart
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    private val bottomSheetProfileDialog = ProfileListBottomSheetDialog()
    private lateinit var adapter: ProfilesRecyclerViewAdapter
    private val viewModel = StatisticFragmentViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentLayoutBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initViews()

        createListeners()






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

    private fun createListeners() {
        lifecycleScope.launchWhenStarted {
            withContext(Dispatchers.IO) {
                viewModel.profilesData
                    .onEach { itemList->
                        adapter.items= itemList
                    }
                    .collect()
            }
        }
    }

    private fun initViews() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarMain)
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetView.bottomSheetRoot)
        binding.accountBalanceTextView.setOnClickListener {  bottomSheetBehavior.state= BottomSheetBehavior.STATE_EXPANDED }
        binding.bottomSheetView.accountRecyclerView

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (!menu.hasVisibleItems()) {
            inflater.inflate(R.menu.statistic_fragmet_appbar_menu, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile_option_menu_button ->{bottomSheetProfileDialog.show(childFragmentManager, "")}
            R.id.sort_option_menu_button -> {}
        }
        return super.onOptionsItemSelected(item)
    }


    private fun dataValues(): ArrayList<PieEntry> {
        return arrayListOf(PieEntry(15F, "Hello"), PieEntry(20F, "Hey"), PieEntry(30F, "OOO"))
    }


}
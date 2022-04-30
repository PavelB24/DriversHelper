package ru.barinov.drivershelper.ui

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.google.android.material.bottomsheet.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.*
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent
import ru.barinov.drivershelper.R
import ru.barinov.drivershelper.core.CircleDrawable
import ru.barinov.drivershelper.databinding.*
import ru.barinov.drivershelper.ui.recyclerViews.AccountMovementsRecyclerViewAdapter

class StatisticFragment : Fragment() {

    private lateinit var binding: HomeFragmentLayoutBinding
    private lateinit var pieChart: PieChart
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    private val sharedPreferences = KoinJavaComponent.inject<SharedPreferences>(SharedPreferences::class.java)
    private val adapter = AccountMovementsRecyclerViewAdapter()
    private val viewModel by sharedViewModel<StatisticFragmentViewModel>{
        parametersOf(sharedPreferences.value.getString(SHARED_PREFS_AND_ARGS_KEY, ""))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentLayoutBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initViews()

        viewModel.doShit()

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

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun createListeners() {

        lifecycleScope.launchWhenStarted {
            withContext(Dispatchers.Main) {
                viewModel.profileToolbarData.onEach { model ->
                    if (model != null) {
                        binding.toolbarTextView.text = model.name
                        binding.toolbarShapeableImageView.setImageBitmap(model.icon)
                        if (model.icon == null){
                            binding.toolbarShapeableImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_profile_img_placeholder))
                        }

                        val editor: SharedPreferences.Editor = sharedPreferences.value.edit()
                        editor.putString("profileId", model.id).apply()
                    }
                }.collect()

            }
        }
        lifecycleScope.launchWhenStarted {
            withContext(Dispatchers.Main){
                viewModel.balanceData.onEach {list->
                    Log.d("@@@", list.size.toString())
                    adapter.setItems(list)
                    var totalValue = 0f
                    list.onEach { totalValue +=
                        it.value }
                    binding.accountBalanceTextView.text = totalValue.toString()

                }.collect()
            }
        }
    }

    private fun initViews() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarMain)
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetView.bottomSheetRoot)
        binding.accountBalanceTextView.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        binding.bottomSheetView.accountRecyclerView.adapter= adapter
        binding.negativeChange.setOnClickListener {
            val bundle: Bundle =  Bundle().apply {
                putString(
                    SHARED_PREFS_AND_ARGS_KEY,
                    viewModel.profileId.value )}
            findNavController()
                .navigate(
                    R.id.action_statisticFragment_to_balanceChangeDialog,
                    bundle)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (!menu.hasVisibleItems()) {
            inflater.inflate(R.menu.statistic_fragmet_appbar_menu, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profile_option_menu_button -> {
                findNavController().navigate(R.id.action_statisticFragment_to_profileListBottomSheetDialog)
            }
            R.id.sort_option_menu_button -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    private fun dataValues(): ArrayList<PieEntry> {
        return arrayListOf(PieEntry(15F, "Hello"), PieEntry(20F, "Hey"), PieEntry(30F, "OOO"))
    }

    companion object{
        const val SHARED_PREFS_AND_ARGS_KEY= "profileId"
    }

}
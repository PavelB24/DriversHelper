package ru.barinov.drivershelper.ui

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.barinov.drivershelper.R
import ru.barinov.drivershelper.databinding.ActivityMainLayoutBinding

class ActivityMain: AppCompatActivity(), ActivityRouterInterface {

    private lateinit var binding: ActivityMainLayoutBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_DriversHelper)
        binding= ActivityMainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)



        bottomNavigationView= binding.bottomNavigationView
        bottomNavigationView.setOnItemSelectedListener {item ->
            when(item.itemId){
                R.id.statistic_item_nav_menu ->{
                    openStatisticFragment()
                }
                R.id.routs_map_item_nav_menu ->{
                    openMapsFragment()
                }
            }; true
        }
        bottomNavigationView.selectedItemId= R.id.statistic_item_nav_menu

    }

    private fun openStatisticFragment() {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction().replace(binding.mainContainerForFragments.id, StatisticFragment()).commit()
    }

    private fun openMapsFragment() {

    }

     override fun openProfileCreationFragment(){
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction().add(R.id.main_container_for_fragments, ProfileCreationFragment()).addToBackStack(null).commit()
    }

}
//val colorArray= mutableListOf<Int>(Color.BLACK, Color.RED, Color.GRAY)
//        val pieDataSet = PieDataSet(dataValues(), "")
//        pieDataSet.colors= colorArray
//        val pieData = PieData(pieDataSet)
//        pieData.setValueTextSize(20F)
//
//        pieChart=binding.pieChart
//        pieChart.data= pieData
//        pieChart.setUsePercentValues(true)
//        pieChart.setDrawEntryLabels(false)
//private fun dataValues(): ArrayList<PieEntry> {
//    return arrayListOf(PieEntry(15F, "Hello"), PieEntry(20F, "Hey"), PieEntry(30F, "OOO"))
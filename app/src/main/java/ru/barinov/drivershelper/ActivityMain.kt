package ru.barinov.drivershelper

import android.graphics.Color
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.barinov.drivershelper.UI.StatisticFragment
import ru.barinov.drivershelper.databinding.ActivityMainLayoutBinding

class ActivityMain: AppCompatActivity() {

    private lateinit var binding: ActivityMainLayoutBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityMainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        bottomNavigationView= binding.bottomNavigationView
        bottomNavigationView.setOnItemSelectedListener {item ->
            when(item.itemId){
                R.id.statistic_item_nav_menu->{
                    openStatisticFragment()
                }
                R.id.routs_map_item_nav_menu->{
                    openMapsFragment()
                }
            }; true
        }
        bottomNavigationView.selectedItemId= R.id.statistic_item_nav_menu

    }

    private fun openStatisticFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.main_container_for_fragments, StatisticFragment()).commit()
    }

    private fun openMapsFragment() {

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
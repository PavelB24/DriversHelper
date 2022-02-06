package ru.barinov.drivershelper.ui

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.barinov.drivershelper.R
import ru.barinov.drivershelper.databinding.ActivityMainLayoutBinding

class ActivityMain: AppCompatActivity() {

    private lateinit var binding: ActivityMainLayoutBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_DriversHelper)
        binding= ActivityMainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)



        bottomNavigationView= binding.bottomNavigationView


        val navCon = findNavController(R.id.navHostFragment)

        bottomNavigationView.setupWithNavController(navCon)


    }

    companion object{
        const val sharedPreferencesName = "appSharedPreferencesName"
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
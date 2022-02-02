package ru.barinov.drivershelper.ui.recyclerViews

import android.graphics.Bitmap
import androidx.room.*
import ru.barinov.drivershelper.domain.models.*
import ru.barinov.drivershelper.domain.typeConventers.*

data class ProfileRecyclerItem(
    val id: String,

    val userName: String,

    var type: ProfileType,

    var fuelType: FuelType,

    var averageFuelConsume: Float,

    var depreciationOfMaintenance: Float,

    val image: Bitmap?,

    val creationTime: Long
)



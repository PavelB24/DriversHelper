package ru.barinov.drivershelper.ui.recyclerViews

import android.graphics.Bitmap
import androidx.room.*
import ru.barinov.drivershelper.domain.models.*
import ru.barinov.drivershelper.domain.typeConventers.*

data class ProfileRecyclerItem(
    val id: String,

    val userName: String,

    val type: ProfileType,

    val fuelType: FuelType,

    var averageFuelConsume: Float,

    val depreciationOfMaintenance: Float,

    val image: Bitmap?,

    val creationTime: Long,

    val listener: ProfileItemClickListener
)



package ru.barinov.drivershelper.domain.models

import androidx.room.*

@Entity(tableName = "rout_points")
data class RoadPointEntity(

    @PrimaryKey
    private val id: String,

    var latitude: Double,

    var longitude: Double,
)
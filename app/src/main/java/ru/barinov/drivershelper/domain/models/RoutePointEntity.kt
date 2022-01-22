package ru.barinov.drivershelper.domain.models

import androidx.room.*

@Entity(
    tableName = "rout_points",
    foreignKeys = [ForeignKey(
        entity = RouteEntity::class,
        parentColumns = ["route_id"],
        childColumns = ["route_id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class RoutePointEntity(

    @PrimaryKey
    @ColumnInfo(name = "point_id")
     val id: String,

    @ColumnInfo(name = "route_id")
     val routId: String,

    var latitude: Double,

    var longitude: Double,
)
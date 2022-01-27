package ru.barinov.drivershelper.domain.models

import androidx.room.*

@Entity(
    tableName = "routes",
    foreignKeys = [ForeignKey(
        entity = ProfileEntity::class,
        parentColumns = ["id"],
        childColumns = ["profile_id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)

data class RouteEntity(

    @PrimaryKey
    @ColumnInfo(name = "route_id")
    val Id: String,

    @ColumnInfo(name = "profile_id")
    val profileId: String,

    @ColumnInfo(name = "route_leigh")
    var routLeigh: Float,

    @ColumnInfo(name = "rate_per_route")
    var ratePerRoute: Float

)


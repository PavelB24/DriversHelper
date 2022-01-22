package ru.barinov.drivershelper.data

import androidx.room.*
import ru.barinov.drivershelper.domain.models.RoutePointEntity

data class RoutWithRoutePoints(

    @ColumnInfo(name = "route_id")
    val Id: String,

    @ColumnInfo(name = "profile_id")
    val profileId: String,

    @ColumnInfo(name = "route_leigh")
    var routLeigh: Float,

    @Relation(parentColumn = "route_id", entityColumn = "route_id", entity = RoutePointEntity::class)
    val routePoints: List<RoutePointEntity>


)
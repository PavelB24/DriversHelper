package ru.barinov.drivershelper.domain.models

import androidx.room.*
import ru.barinov.drivershelper.domain.models.RoutePointEntity

data class RoutWithRoutePoints(

    @ColumnInfo(name = "route_id")
    val Id: String,

    @ColumnInfo(name = "profile_id")
    val profileId: String,

    @ColumnInfo(name = "route_leigh")
    var routLeigh: Float,

    @ColumnInfo(name = "rate_per_route")
    var ratePerRoute: Float,

    @Relation(parentColumn = "route_id", entityColumn = "route_id", entity = RoutePointEntity::class)
    val routePoints: List<RoutePointEntity>


)
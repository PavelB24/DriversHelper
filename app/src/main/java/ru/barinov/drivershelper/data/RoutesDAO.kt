package ru.barinov.drivershelper.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.barinov.drivershelper.domain.models.*

@Dao
interface RoutesDAO {

    @Transaction
    @Query("SELECT * FROM routes WHERE profile_id == :profileId")
    fun getRoutsWithRoutePoints(profileId: String): Flow<List<RoutWithRoutePoints>>


    @Query("DELETE FROM routes WHERE route_id == :routID")
    suspend fun deleteRoute(routID: String)


    @Query("DELETE FROM rout_points WHERE  point_id = :pointId ")
    suspend fun deleteRoutePoint(pointId: String)


    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRoute(rout: RouteEntity)


    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRoutePoint(routePoint: RoutePointEntity)

}
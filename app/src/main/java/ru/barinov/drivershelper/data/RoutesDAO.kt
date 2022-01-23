package ru.barinov.drivershelper.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.barinov.drivershelper.domain.models.*

@Dao
interface RoutesDAO {

    @Transaction
    @Query("SELECT * FROM routes WHERE profile_id == :profileId")
    fun getRoutsWithRoutPoints(profileId: String): Flow<List<RoutWithRoutePoints>>


    @Query("DELETE FROM routes WHERE route_id == :routID")
    suspend fun deleteRout(routID: String)


    @Query("DELETE FROM rout_points WHERE  point_id = :pointId ")
    suspend fun deleteRoutPoint(pointId: String)


    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRout(rout: RouteEntity)


    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRoutPoint(routPoint: RoutePointEntity)

}
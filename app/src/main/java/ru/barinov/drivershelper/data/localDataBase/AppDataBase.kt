package ru.barinov.drivershelper.data.localDataBase

import androidx.room.*
import ru.barinov.drivershelper.data.*
import ru.barinov.drivershelper.domain.models.*

@Database(
    version = 1,
    entities = [
        ProfileEntity::class,
        RoutePointEntity::class,
        RouteEntity::class,
        AccountMoveEntity::class
    ]
)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getProfileDao(): ProfilesDAO

    abstract fun getRoutesDao(): RoutesDAO

    abstract fun getAccountMovementDao(): AccountDataDAO

}
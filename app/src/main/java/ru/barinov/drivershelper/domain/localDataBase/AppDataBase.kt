package ru.barinov.drivershelper.domain.localDataBase

import androidx.room.*
import ru.barinov.drivershelper.domain.models.*

@Database(
    version = 1,
    entities = [
        ProfileEntity::class,
        RoadPointEntity::class,
        RoutEntity::class
    ]
)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getProfileDao()
}
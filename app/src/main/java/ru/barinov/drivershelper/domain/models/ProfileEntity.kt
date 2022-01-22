package ru.barinov.drivershelper.domain.models

import androidx.room.*
import ru.barinov.drivershelper.domain.typeConventers.*

@Entity(tableName = "profiles")
data class ProfileEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
     val id: String,

    @TypeConverters(ProfileTypeConverter::class)
    var type: ProfileType,

    @TypeConverters(FuelTypeConverter::class)
    var fuelType: FuelType,
    )

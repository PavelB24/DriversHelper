package ru.barinov.drivershelper.domain.models

import androidx.room.*
import ru.barinov.drivershelper.domain.typeConventers.*

@Entity(tableName = "profiles")
data class ProfileEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
     val id: String,

    @ColumnInfo(name = "user_name")
    val userName: String,

    @TypeConverters(ProfileTypeConverter::class)
    var type: ProfileType,

    @TypeConverters(FuelTypeConverter::class)
    var fuelType: FuelType,

    @ColumnInfo(name = "average_fuel_consume")
    var averageFuelConsume: Float,

    @ColumnInfo(name = "depreciation_of_maintenance")
    var depreciationOfMaintenance: Float,

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val image: ByteArray,

    val creationTime: Long
    )

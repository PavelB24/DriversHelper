package ru.barinov.drivershelper.domain.typeConventers

import androidx.room.TypeConverter
import ru.barinov.drivershelper.domain.models.*

class FuelTypeConverter {
    @TypeConverter
    fun toType(value: String) = enumValueOf<FuelType>(value)

    @TypeConverter
    fun fromType(value: FuelType) = value.name
}

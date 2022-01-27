package ru.barinov.drivershelper.domain.typeConventers

import androidx.room.TypeConverter
import ru.barinov.drivershelper.domain.models.*

class AccountMovementTypeTypeConverter {
    @TypeConverter
    fun toType(value: String) = enumValueOf<MovementType>(value)

    @TypeConverter
    fun fromType(value: MovementType) = value.name
}

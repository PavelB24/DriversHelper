package ru.barinov.drivershelper.domain.typeConventers

import androidx.room.TypeConverter
import ru.barinov.drivershelper.domain.models.*

class ProfileTypeConverter {
    @TypeConverter
    fun toType(value: String) = enumValueOf<ProfileType>(value)

    @TypeConverter
    fun fromType(value: ProfileType) = value.name
}
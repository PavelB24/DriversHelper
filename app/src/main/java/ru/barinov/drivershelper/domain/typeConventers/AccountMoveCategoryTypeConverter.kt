package ru.barinov.drivershelper.domain.typeConventers

import androidx.room.TypeConverter
import ru.barinov.drivershelper.domain.models.SpendingCategory

class AccountMoveCategoryTypeConverter {
    @TypeConverter
    fun toType(value: Int):SpendingCategory = enumValues<SpendingCategory>()[value]

    @TypeConverter
    fun fromType(value: SpendingCategory):Int = value.ordinal
}
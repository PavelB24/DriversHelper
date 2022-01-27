package ru.barinov.drivershelper.domain.typeConventers

import androidx.room.TypeConverter
import ru.barinov.drivershelper.domain.models.AccountMoveCategory

class AccountMoveCategoryTypeConverter {
    @TypeConverter
    fun toType(value: String) = enumValueOf<AccountMoveCategory>(value)

    @TypeConverter
    fun fromType(value: AccountMoveCategory) = value.name
}
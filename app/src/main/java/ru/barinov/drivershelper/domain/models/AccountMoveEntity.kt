package ru.barinov.drivershelper.domain.models

import androidx.room.*
import ru.barinov.drivershelper.domain.typeConventers.*

@Entity(
    tableName = "account_movements",
    foreignKeys = [ForeignKey(
        entity = ProfileEntity::class,
        parentColumns = ["id"],
        childColumns = ["profile_id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class AccountMoveEntity(

    @PrimaryKey
    @ColumnInfo(name = "profile_id")
    private val profileId: String,

    @TypeConverters(AccountMovementTypeConverter::class)
    private val accountMovementType: MovementType,

    @TypeConverters(AccountMoveCategoryTypeConverter::class)
    private val category: AccountMoveCategory
) {}
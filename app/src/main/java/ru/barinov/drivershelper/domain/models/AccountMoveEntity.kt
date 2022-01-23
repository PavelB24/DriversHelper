package ru.barinov.drivershelper.domain.models

import androidx.room.*
import ru.barinov.drivershelper.domain.typeConventers.*

@Entity(
    tableName = "account_movements",
    primaryKeys = ["profile_id", "movement_id" ],
    foreignKeys = [ForeignKey(
        entity = ProfileEntity::class,
        parentColumns = ["id"],
        childColumns = ["profile_id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class AccountMoveEntity(


    @ColumnInfo(name = "profile_id")
     val profileId: String,

    @ColumnInfo(name = "movement_id")
     val movementKey:String,

    @TypeConverters(AccountMovementTypeConverter::class)
     val accountMovementType: MovementType,

    @TypeConverters(AccountMoveCategoryTypeConverter::class)
     val category: AccountMoveCategory,

     val value: Float
)
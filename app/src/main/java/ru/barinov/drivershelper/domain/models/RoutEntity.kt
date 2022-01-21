package ru.barinov.drivershelper.domain.models

import androidx.room.*

@Entity(
    tableName = "routs",
    primaryKeys = ["profile_id", "rout_id"],
    foreignKeys = [
        ForeignKey(
            entity = ProfileEntity::class,
            parentColumns = ["id"],
            childColumns = ["profile_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = RoadPointEntity::class,
            parentColumns = ["id"],
            childColumns = ["point_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)

data class RoutEntity(

    @ColumnInfo(name = "profile_id") private val profileId: String,

    @ColumnInfo(name = "point_id") private val routPointId: List<String> ,

)


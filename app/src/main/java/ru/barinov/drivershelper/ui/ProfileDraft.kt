package ru.barinov.drivershelper.ui

import ru.barinov.drivershelper.domain.models.*

data class ProfileDraft(
    val profileImage: ByteArray?,
    val userName: String,
    val profileType: ProfileType,
    val fuelType: FuelType,
    val fuelConsume: String,
    val depreciationOfMaintenance: String
)




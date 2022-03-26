package ru.barinov.drivershelper.ui

import ru.barinov.drivershelper.domain.models.*

data class AccountMoveDraft(
    val profileId: String,
    val value: Float,
    val accountMovementType: MovementType,
    val category: SpendingCategory
)

package ru.barinov.drivershelper.ui

import androidx.lifecycle.*
import kotlinx.coroutines.*
import ru.barinov.drivershelper.data.localDataBase.AccountBalanceRepository
import ru.barinov.drivershelper.domain.models.AccountMoveEntity
import java.util.*

class BalanceChangeDialogViewModel(
    private val repository: AccountBalanceRepository
): ViewModel() {

    fun addMovement(draft: AccountMoveDraft) {
        viewModelScope.launch(Dispatchers.IO) {
            val entity = AccountMoveEntity(draft.profileId,
            UUID.randomUUID().toString(),
            draft.accountMovementType,
            draft.category,
            draft.value,
            System.currentTimeMillis())
            repository.addMove(entity)
        }

    }
}
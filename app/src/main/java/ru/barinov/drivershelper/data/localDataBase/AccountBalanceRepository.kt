package ru.barinov.drivershelper.data.localDataBase

import kotlinx.coroutines.flow.Flow
import ru.barinov.drivershelper.data.AccountDataDAO
import ru.barinov.drivershelper.domain.models.AccountMoveEntity

class AccountBalanceRepository(
    private val dao: AccountDataDAO
) {

    fun getMovementsByProfileId(profileId: String): Flow<List<AccountMoveEntity>>{
        return dao.getAccountMovementsByProfileId(profileId)
    }

    suspend fun deleteMove(id: String){
        dao.deleteAccountMovement(id)
    }

    suspend fun addMove(movementEntity: AccountMoveEntity){
        dao.addAccountMovement(movementEntity)
    }



}
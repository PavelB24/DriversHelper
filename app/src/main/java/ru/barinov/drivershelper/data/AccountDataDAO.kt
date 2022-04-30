package ru.barinov.drivershelper.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.barinov.drivershelper.domain.models.*

@Dao
interface AccountDataDAO {

    @Query("SELECT * FROM account_movements WHERE profile_id == :profileId")
    fun getAccountMovementsByProfileId(profileId: String): Flow<List<AccountMoveEntity>>

    @Query("DELETE FROM account_movements WHERE movement_id == :movementKey")
    suspend fun deleteAccountMovement(movementKey: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAccountMovement(movementEntity: AccountMoveEntity)

    @Query("SELECT * FROM account_movements WHERE :type =1 AND profile_id = :profileId ")
    fun getThatType(profileId: String, type: Int): Flow<List<AccountMoveEntity>>


}
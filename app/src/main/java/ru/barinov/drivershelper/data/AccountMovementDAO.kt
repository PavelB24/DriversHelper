package ru.barinov.drivershelper.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.barinov.drivershelper.domain.models.*

@Dao
interface AccountMovementDAO {

    @Query("SELECT * FROM account_movements WHERE profile_id == :profileId")
    fun getAccountMovementById(profileId: String): Flow<List<AccountMoveEntity>>

    @Query("DELETE FROM account_movements WHERE movement_id == :movementKey")
    suspend fun deleteAccountMovement(movementKey: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRout(movement: AccountMoveEntity)

}
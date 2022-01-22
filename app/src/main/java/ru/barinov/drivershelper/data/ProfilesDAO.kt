package ru.barinov.drivershelper.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.barinov.drivershelper.domain.models.ProfileEntity

@Dao
interface ProfilesDAO {


    @Query("SELECT * FROM profiles")
    fun getProfiles(): Flow<List<ProfileEntity>>


    @Query("DELETE FROM profiles WHERE id == :profileId")
    suspend fun deleteProfile(profileId: String)
}
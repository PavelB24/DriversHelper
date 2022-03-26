package ru.barinov.drivershelper.data

import kotlinx.coroutines.flow.Flow
import ru.barinov.drivershelper.data.ProfilesDAO
import ru.barinov.drivershelper.domain.models.ProfileEntity

class ProfilesRepository(
    private val dao: ProfilesDAO
) {

    suspend fun addProfile(profile: ProfileEntity) {
        dao.addProfile(profile)
    }

    suspend fun getProfileById(id: String): ProfileEntity? {
        return dao.getProfilesById(id)
    }

    fun getProfiles(): Flow<List<ProfileEntity>> {
        return dao.getProfiles()
    }

}
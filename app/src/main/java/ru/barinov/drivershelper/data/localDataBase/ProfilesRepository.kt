package ru.barinov.drivershelper.data.localDataBase

import kotlinx.coroutines.flow.Flow
import ru.barinov.drivershelper.data.ProfilesDAO
import ru.barinov.drivershelper.domain.models.ProfileEntity

class ProfilesRepository(
    private val dao: ProfilesDAO
) {

   suspend fun addProfile(profile: ProfileEntity){
        dao.addProfile(profile)
    }


     fun getProfiles(): Flow<List<ProfileEntity>> {
        return dao.getProfiles()
    }

    fun getProfileById(id: String):Flow<ProfileEntity>{
        return dao.getProfileById(id)
    }
    
}
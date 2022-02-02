package ru.barinov.drivershelper.ui

import android.graphics.Bitmap
import androidx.lifecycle.*
import kotlinx.coroutines.*

import ru.barinov.drivershelper.data.localDataBase.ProfilesRepository
import ru.barinov.drivershelper.domain.models.ProfileEntity
import java.io.ByteArrayOutputStream
import java.util.*

class ProfileCreationViewModel(
    private val profilesRepository: ProfilesRepository
) : ViewModel() {

    private lateinit var  uuid: UUID

    fun createProfile(draft: ProfileDraft) {
        uuid= UUID.randomUUID()
        val profileEntity = ProfileEntity(
            uuid.toString(),
            draft.userName,
            draft.profileType,
            draft.fuelType,
            draft.fuelConsume.toFloat(),
            draft.depreciationOfMaintenance.toFloat(),
            draft.profileImage,
            System.currentTimeMillis())

        viewModelScope.launch {
            withContext(Dispatchers.IO){
            profilesRepository.addProfile(profileEntity)}
        }

    }

    fun bitmapToByteArray(bitmap: Bitmap){
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos)
        val bitmapArray = bos.toByteArray()
        bos.close()

    }

}
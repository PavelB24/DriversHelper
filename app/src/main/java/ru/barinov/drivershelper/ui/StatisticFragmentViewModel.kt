package ru.barinov.drivershelper.ui

import android.graphics.*
import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import ru.barinov.drivershelper.core.scaledImageFromBitmap
import ru.barinov.drivershelper.data.localDataBase.ProfilesRepository
import ru.barinov.drivershelper.domain.models.ProfileEntity
import ru.barinov.drivershelper.ui.recyclerViews.ProfileRecyclerItem

private const val imageScaleBase = 100

class StatisticFragmentViewModel(
    private val profilesRepository: ProfilesRepository,
    private val displayScale: Float
): ViewModel() {

    private val _profilesData: Flow<List<ProfileEntity>> = profilesRepository.getProfiles()
    val profilesData = _profilesData
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
        .map { entityList ->
                val recyclerItems = entityList.map { entity ->
                    ProfileRecyclerItem(
                        id = entity.id,
                        userName = entity.userName,
                        type = entity.type,
                        fuelType = entity.fuelType,
                        averageFuelConsume = entity.averageFuelConsume,
                        depreciationOfMaintenance = entity.depreciationOfMaintenance,
                        image = createBitMap(image = entity.image),
                        creationTime = entity.creationTime
                        )
                }
                return@map recyclerItems
            }

    private suspend fun createBitMap(image: ByteArray?): Bitmap= suspendCancellableCoroutine {continuation->
        if(image!= null){
            val bitmap= BitmapFactory.decodeByteArray( image, 0, image.size)
            val scaledBitMap = bitmap.scaledImageFromBitmap(bitmap, imageScaleBase, displayScale)
            continuation.resume(scaledBitMap, null)
        }

    }
}
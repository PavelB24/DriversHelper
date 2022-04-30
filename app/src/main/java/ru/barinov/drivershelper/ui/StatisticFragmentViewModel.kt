package ru.barinov.drivershelper.ui

import android.graphics.*
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import ru.barinov.drivershelper.core.*
import ru.barinov.drivershelper.data.ProfilesRepository
import ru.barinov.drivershelper.data.localDataBase.*
import ru.barinov.drivershelper.domain.models.*
import ru.barinov.drivershelper.ui.recyclerViews.*

private const val imageScaleBase = 100

class StatisticFragmentViewModel(
    private val profilesRepository: ProfilesRepository,
    private val balanceRepository: AccountBalanceRepository,
    private val onStartCurrentUsersId: String,
    private val displayScale: Float
): ViewModel(), ProfileItemClickListener {



    //TODO заменить начальное значение на значение из onStartCurrentUsersId
    private val _profileId: MutableStateFlow<String> = MutableStateFlow(onStartCurrentUsersId)
    val profileId: StateFlow<String> = _profileId

    val balanceData = _profileId.flatMapLatest { id->
        balanceRepository.getSpecMoves(id, 2)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val _profileSelected: MutableStateFlow<Event<Boolean>> = MutableStateFlow(Event(false))
    val profileSelected: StateFlow<Event<Boolean>> = _profileSelected

    private val _profileToolbarData: Flow<ProfileEntity?> = profileId.flatMapLatest {id->
        val profileEntity: ProfileEntity? = profilesRepository.getProfileById(id)
        return@flatMapLatest flowOf(profileEntity)
    }
        .flowOn(Dispatchers.IO)



    val profileToolbarData = _profileToolbarData
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)
        .map { profileEntity ->

            if (profileEntity != null){
            return@map StatisticFragmentProfileModel(
                id = profileEntity.id,
                icon = createBitMap(profileEntity.image),
                name = profileEntity.userName
            )
            } else{
                return@map null
            }
        }




    private val _profilesData: Flow<List<ProfileEntity>> = profilesRepository.getProfiles()
    val profilesData = _profilesData
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
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
                        creationTime = entity.creationTime,
                        listener = sendListener()
                    )
                }
                return@map recyclerItems
            }
        .flowOn(Dispatchers.Default)


    private  fun sendListener(): ProfileItemClickListener {
      return object: ProfileItemClickListener{
            override fun onItemClick(item: ProfileRecyclerItem) {
                Log.d("@@@@", "onBindViewHolder:@ ")
                _profileId.value = item.id
                _profileSelected.value = Event(true)
            }
        }
    }

    private suspend fun createBitMap(image: ByteArray?): Bitmap?= suspendCancellableCoroutine {continuation->
        if(image!= null){
            val bitmap= BitmapFactory.decodeByteArray( image, 0, image.size)
            val scaledBitMap = bitmap.scaledImageFromBitmap(bitmap, imageScaleBase, displayScale)
            continuation.resume(scaledBitMap, null)
        } else{
            continuation.resume(null, null)
        }

    }

    override fun onItemClick(item: ProfileRecyclerItem) {
        _profileId.value = item.id
    }

    fun doShit() {
//        viewModelScope.launch {
//            balanceRepository.addMove(AccountMoveEntity("1", "51561", MovementType.EXPENSE, SpendingCategory.OTHER, 51f, 5615156L))
        }

    }
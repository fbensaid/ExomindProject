package com.farouk.exomindtest.presenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.farouk.exomindtest.data.model.PhotoResponse
import com.farouk.exomindtest.data.remoteApi.Apifactory
import com.farouk.exomindtest.data.repository.PhotosRepository
import com.farouk.exomindtest.utils.DebugLog
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PhotosViewModel : ViewModel() {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    private val handler = CoroutineExceptionHandler { _, exception ->
        DebugLog.e("Couroutine", "Caught $exception")
    }
    private val photosRepository: PhotosRepository = PhotosRepository(Apifactory.Api)
    val photoLiveData = MutableLiveData<MutableList<PhotoResponse>>()

    fun getPhotosByAlbums(albumId:String) {
        scope.launch(handler) {
            val photos = photosRepository.getPhotosByAlbum(albumId)
            photoLiveData.postValue(photos)
        }
    }

    fun cancelRequests() = coroutineContext.cancel()


    override fun onCleared() {
        super.onCleared()
        this.parentJob.cancel()
    }
}
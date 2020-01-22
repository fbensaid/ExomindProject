package com.farouk.exomindtest.presenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.farouk.exomindtest.data.model.AlbumResponse
import com.farouk.exomindtest.data.remoteApi.Apifactory
import com.farouk.exomindtest.data.repository.AlbumsRepository
import com.farouk.exomindtest.utils.DebugLog
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AlbumsViewModel : ViewModel() {
    //create a new Job
    private val parentJob = Job()
    //create a coroutine context with the job and the dispatcher
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    //create a coroutine scope with the coroutine context
    private val scope = CoroutineScope(coroutineContext)
    private val handler = CoroutineExceptionHandler { _, exception ->
        DebugLog.e("Couroutine", "Caught $exception")
    }

    //initialize news repo
    private val albumsRepository: AlbumsRepository = AlbumsRepository(Apifactory.Api)
    //live data that will be populated as news updates
    val albumsLiveData = MutableLiveData<MutableList<AlbumResponse>>()

    fun getAlbumsByUser(userId:String) {
        ///launch the coroutine scope
        scope.launch(handler) {
            //get latest albums from albums repo
            val albums = albumsRepository.getAlbumsByUser(userId)
            //post the value inside live data
            albumsLiveData.postValue(albums)
        }
    }

    fun cancelRequests() = coroutineContext.cancel()


    override fun onCleared() {
        super.onCleared()
        this.parentJob.cancel()
    }
}
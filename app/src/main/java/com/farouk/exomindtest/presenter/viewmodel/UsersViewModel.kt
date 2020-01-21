package com.farouk.exomindtest.presenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.farouk.exomindtest.data.repository.UserRepository
import com.farouk.exomindtest.data.model.user.UserResponse
import com.farouk.exomindtest.data.remoteApi.Apifactory
import com.farouk.exomindtest.utils.DebugLog
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class UsersViewModel : ViewModel() {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    private val handler = CoroutineExceptionHandler { _, exception ->
        DebugLog.e("Couroutine", "Caught $exception")
    }
    private val usersRepository: UserRepository = UserRepository(Apifactory.Api)
    val userLiveData = MutableLiveData<MutableList<UserResponse>>()

    fun getUsers() {
        scope.launch(handler) {
            val users = usersRepository.getUsers()
            userLiveData.postValue(users)
        }
    }

    override fun onCleared() {
        super.onCleared()
        this.parentJob.cancel()
    }
}
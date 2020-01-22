package com.farouk.exomindtest.presenter.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.farouk.exomindtest.presenter.viewmodel.AlbumsViewModel

@Suppress("UNCHECKED_CAST")
class AlbumsViewModelFactory() : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AlbumsViewModel() as T
    }
}
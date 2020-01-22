package com.farouk.exomindtest.presenter.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.farouk.exomindtest.presenter.viewmodel.PhotosViewModel

@Suppress("UNCHECKED_CAST")
class PhotosViewModelFactory() : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PhotosViewModel() as T
    }
}
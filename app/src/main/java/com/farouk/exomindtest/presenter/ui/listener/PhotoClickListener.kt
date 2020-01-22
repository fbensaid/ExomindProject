package com.farouk.exomindtest.presenter.ui.listener

import android.view.View
import com.farouk.exomindtest.data.model.PhotoResponse

interface PhotoClickListener {
    fun onRecyclerViewItemClick(view: View, photo: PhotoResponse)
}

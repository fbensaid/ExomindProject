package com.farouk.exomindtest.presenter.ui.listener

import android.view.View
import com.farouk.exomindtest.data.model.AlbumResponse


interface AlbumsClickListener {
    fun onRecyclerViewItemClick(view: View, album: AlbumResponse)
}

package com.farouk.exomindtest.presenter.ui.listener

import android.view.View
import com.farouk.exomindtest.data.model.user.UserResponse


interface UsersClickListener {
    fun onRecyclerViewItemClick(view: View, labo: UserResponse)
}

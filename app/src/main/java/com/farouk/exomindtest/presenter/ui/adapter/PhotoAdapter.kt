package com.farouk.exomindtest.presenter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.farouk.exomindtest.R
import com.farouk.exomindtest.data.model.AlbumResponse
import com.farouk.exomindtest.data.model.PhotoResponse
import com.farouk.exomindtest.data.model.user.UserResponse
import com.farouk.exomindtest.databinding.RecycleviewAlbumsBinding
import com.farouk.exomindtest.databinding.RecycleviewPhotosBinding
import com.farouk.exomindtest.databinding.RecycleviewUserBinding
import com.farouk.exomindtest.presenter.ui.listener.AlbumsClickListener
import com.farouk.exomindtest.presenter.ui.listener.PhotoClickListener
import com.farouk.exomindtest.presenter.ui.listener.UsersClickListener

class PhotoAdapter(
    private val listofphotoResponse: List<PhotoResponse>,
    private val listner: PhotoClickListener

) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    private var listofPhoto = listofphotoResponse

    override fun getItemCount(): Int {
        return listofPhoto.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PhotoViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycleview_photos,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.recycleviewPhotoBinding.photoListResponseData =
            listofPhoto[position]

        holder.recycleviewPhotoBinding.cardViewPhoto.setOnClickListener {
            listner.onRecyclerViewItemClick(
                holder.recycleviewPhotoBinding.cardViewPhoto,
                listofPhoto[position]
           )
        }
    }


    inner class PhotoViewHolder(
        val recycleviewPhotoBinding: RecycleviewPhotosBinding
    ) : RecyclerView.ViewHolder(recycleviewPhotoBinding.root)
}



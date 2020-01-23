package com.farouk.exomindtest.presenter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.farouk.exomindtest.R
import com.farouk.exomindtest.data.model.PhotoResponse
import com.farouk.exomindtest.databinding.RecycleviewPhotosBinding
import com.farouk.exomindtest.presenter.ui.listener.PhotoClickListener
import com.squareup.picasso.Picasso

class PhotoAdapter(
      listOfPhotoResponse: List<PhotoResponse>,
    private val listener: PhotoClickListener

) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    private var listOfPhoto = listOfPhotoResponse

    override fun getItemCount(): Int {
        return listOfPhoto.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PhotoViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycleview_photos,
            parent,
            false
        )
    )

    companion object {
        @JvmStatic
        @BindingAdapter("android:src")
        fun setImageUri(view: ImageView, imageUri: String?) {
            Picasso.get().load(imageUri).into(view)
        }
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.recycleviewPhotoBinding.photoListResponseData =
            listOfPhoto[position]

        holder.recycleviewPhotoBinding.cardViewPhoto.setOnClickListener {
            listener.onRecyclerViewItemClick(
                holder.recycleviewPhotoBinding.cardViewPhoto,
                listOfPhoto[position]
           )
        }
    }


    inner class PhotoViewHolder(
        val recycleviewPhotoBinding: RecycleviewPhotosBinding
    ) : RecyclerView.ViewHolder(recycleviewPhotoBinding.root)
}



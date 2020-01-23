package com.farouk.exomindtest.presenter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.farouk.exomindtest.R
import com.farouk.exomindtest.data.model.AlbumResponse
import com.farouk.exomindtest.databinding.RecycleviewAlbumsBinding
import com.farouk.exomindtest.presenter.ui.listener.AlbumsClickListener


class AlbumAdapter(
     listOfAlbumResponse: List<AlbumResponse>,
     private var listener: AlbumsClickListener

) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {
    private var listOfAlbums = listOfAlbumResponse

    override fun getItemCount(): Int {
        return listOfAlbums.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AlbumViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycleview_albums,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.recycleviewAlbumsBinding.albumListResponseData =
            listOfAlbums[position]

        holder.recycleviewAlbumsBinding.cardViewAlbums.setOnClickListener {
            listener.onRecyclerViewItemClick(
                holder.recycleviewAlbumsBinding.cardViewAlbums,
                listOfAlbums[position]
           )
        }
    }


    inner class AlbumViewHolder(
        val recycleviewAlbumsBinding: RecycleviewAlbumsBinding
    ) : RecyclerView.ViewHolder(recycleviewAlbumsBinding.root)
}



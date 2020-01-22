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
    private val listofAlbumResponse: List<AlbumResponse>,
    private val listner: AlbumsClickListener

) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {
    private var listofAlbums = listofAlbumResponse

    override fun getItemCount(): Int {
        return listofAlbums.size
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
            listofAlbums[position]

        holder.recycleviewAlbumsBinding.cardViewAlbums.setOnClickListener {
            listner.onRecyclerViewItemClick(
                holder.recycleviewAlbumsBinding.cardViewAlbums,
                listofAlbums[position]
           )
        }
    }


    inner class AlbumViewHolder(
        val recycleviewAlbumsBinding: RecycleviewAlbumsBinding
    ) : RecyclerView.ViewHolder(recycleviewAlbumsBinding.root)
}



package com.farouk.exomindtest.presenter.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.farouk.exomindtest.R
import com.farouk.exomindtest.data.model.AlbumResponse
import com.farouk.exomindtest.presenter.factory.AlbumsViewModelFactory
import com.farouk.exomindtest.presenter.ui.adapter.AlbumAdapter
import com.farouk.exomindtest.presenter.ui.listener.AlbumsClickListener
import com.farouk.exomindtest.presenter.viewmodel.AlbumsViewModel
import kotlinx.android.synthetic.main.fragment_albums.*



class AlbumsFragment : BaseFragment(), AlbumsClickListener {

    private lateinit var albumsViewModel: AlbumsViewModel
    private lateinit var albumsAdapter: AlbumAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_albums, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRepoAlbums()
    }

    private fun setRepoAlbums() {
        val factory = AlbumsViewModelFactory()
        albumsViewModel = ViewModelProviders.of(this, factory).get(AlbumsViewModel::class.java)
        albumsViewModel.getAlbumsByUser(AlbumsFragmentArgs.fromBundle(arguments!!).userId)

        albumsViewModel.albumsLiveData.observe(viewLifecycleOwner, Observer { albums ->
            recycleview_album.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                albumsAdapter = AlbumAdapter(albums, this)
                it.adapter = albumsAdapter
            }
        })
    }

    override fun onRecyclerViewItemClick(view: View, album: AlbumResponse) {

    }



}


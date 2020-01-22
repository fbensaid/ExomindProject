package com.farouk.exomindtest.presenter.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.farouk.exomindtest.R
import com.farouk.exomindtest.data.model.PhotoResponse
import com.farouk.exomindtest.presenter.factory.PhotosViewModelFactory
import com.farouk.exomindtest.presenter.ui.adapter.PhotoAdapter
import com.farouk.exomindtest.presenter.ui.listener.PhotoClickListener
import com.farouk.exomindtest.presenter.viewmodel.PhotosViewModel
import kotlinx.android.synthetic.main.fragment_photo.*


class PhotosFragment : BaseFragment(), PhotoClickListener {

    private lateinit var photoViewModel: PhotosViewModel
    private lateinit var photoAdapter: PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRepoPhoto()
    }

    private fun setRepoPhoto() {
        val factory = PhotosViewModelFactory()
        photoViewModel = ViewModelProviders.of(this, factory).get(PhotosViewModel::class.java)
        photoViewModel.getPhotosByAlbums(PhotosFragmentArgs.fromBundle(arguments!!).albumId)

        photoViewModel.photoLiveData.observe(viewLifecycleOwner, Observer { albums ->
            recycleview_photos.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                photoAdapter = PhotoAdapter(albums, this)
                it.adapter = photoAdapter
            }
        })
    }


    override fun onRecyclerViewItemClick(view: View, photo: PhotoResponse) {
    }


}


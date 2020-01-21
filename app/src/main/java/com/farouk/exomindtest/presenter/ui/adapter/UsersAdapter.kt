package com.farouk.exomindtest.presenter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.farouk.exomindtest.R
import com.farouk.exomindtest.data.model.user.UserResponse
import com.farouk.exomindtest.databinding.RecycleviewUserBinding
import com.farouk.exomindtest.presenter.ui.listener.UsersClickListener

class UsersAdapter(
    private val listofUser: List<UserResponse>,
    private val listener: UsersClickListener

) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>(), Filterable {
    private var filtredListofUser = listofUser
    private var resultListOfSearch = arrayListOf<UserResponse>()

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                filtredListofUser = if (p0.isNullOrEmpty())
                    listofUser
                else {
                    resultListOfSearch.clear()
                    listofUser.forEach {
                        if (it.toString().toLowerCase().contains(p0.toString()))
                            resultListOfSearch.add(it)
                    }
                    resultListOfSearch
                }
                var filtredResult = FilterResults()
                filtredResult.values = filtredListofUser
                return filtredResult
            }

            override fun publishResults(p0: CharSequence?, filtredResult: FilterResults?) {
                filtredListofUser = listOf()
                filtredListofUser = filtredResult!!.values as List<UserResponse>
                notifyDataSetChanged()
            }
        }
    }


    override fun getItemCount(): Int {
        return filtredListofUser.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycleview_user,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.recycleviewUserBinding.userListResponseData =
            filtredListofUser[position]
        holder.recycleviewUserBinding.cardViewUser.setOnClickListener {
            listener.onRecyclerViewItemClick(
                holder.recycleviewUserBinding.cardViewUser,
                filtredListofUser[position]
           )
        }
    }


    inner class UserViewHolder(
        val recycleviewUserBinding: RecycleviewUserBinding
    ) : RecyclerView.ViewHolder(recycleviewUserBinding.root)
}



package com.example.myapplication.home.listfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ListItemsBinding
import com.example.myapplication.home.listfragment.model.UserModel

class UserListAdapter(val itemClicked: (model: UserModel) -> Unit) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {


    private val diffCallBack = object : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(
            oldItem: UserModel,
            newItem: UserModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: UserModel,
            newItem: UserModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val mDiffer: AsyncListDiffer<UserModel> = AsyncListDiffer(this, diffCallBack)


    fun updateList(newList: List<UserModel>) {
        mDiffer.submitList(newList)
        notifyDataSetChanged()
    }


    override fun getItemId(position: Int): Long {
        return mDiffer.currentList[position].id.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ListItemsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_items,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val tempModel = mDiffer.currentList[position]
        holder.bindView(tempModel)

    }

    override fun getItemCount() = mDiffer.currentList.size


    inner class ViewHolder(itemView: ListItemsBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        private val mBinding: ListItemsBinding = itemView

        fun bindView(model: UserModel) {
            mBinding.model = model

            itemView.setOnClickListener {
                itemClicked.invoke(model)
            }

        }


    }
}
package com.github.hiratasatoshi.sample.aaccontributors.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.github.hiratasatoshi.sample.aaccontributors.R
import com.github.hiratasatoshi.sample.aaccontributors.data.entity.ContributorInfo
import com.github.hiratasatoshi.sample.aaccontributors.databinding.ListItemContributorBinding
import com.github.hiratasatoshi.sample.aaccontributors.presentation.viewmodel.ContributorListViewModel

class ContributorListAdapter(
    private val viewModel: ContributorListViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val itemClickListener: ItemClickListener)
    : RecyclerView.Adapter<ContributorListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ListItemContributorBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ListItemContributorBinding>(
            LayoutInflater.from(parent.context), R.layout.list_item_contributor, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return viewModel.list.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel = viewModel
        holder.binding.index = position
        holder.binding.lifecycleOwner = lifecycleOwner
        itemClickListener
        holder.itemView.setOnClickListener {
            itemClickListener.onContributorClick(viewModel.list.value?.get(position))
        }
    }

    // リストアイテム(Contributor)クリックリスナー
    interface ItemClickListener {
        fun onContributorClick(item: ContributorInfo?)
    }
}
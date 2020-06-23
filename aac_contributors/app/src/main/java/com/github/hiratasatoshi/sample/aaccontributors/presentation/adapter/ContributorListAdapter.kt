package com.github.hiratasatoshi.sample.aaccontributors.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.github.hiratasatoshi.sample.aaccontributors.R
import com.github.hiratasatoshi.sample.aaccontributors.databinding.ListItemContributorBinding
import com.github.hiratasatoshi.sample.aaccontributors.presentation.viewmodel.ContributorListViewModel

class ContributorListAdapter(
    private val viewModel: ContributorListViewModel,
    private val lifecycleOwner: LifecycleOwner)
    : RecyclerView.Adapter<ContributorListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ListItemContributorBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ListItemContributorBinding>(
            LayoutInflater.from(parent.context), R.layout.list_item_contributor, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return viewModel.list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel = viewModel
        holder.binding.index = position
        holder.binding.lifecycleOwner = lifecycleOwner
    }
}
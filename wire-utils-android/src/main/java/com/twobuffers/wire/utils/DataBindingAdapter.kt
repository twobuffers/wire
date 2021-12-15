package com.twobuffers.wire.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class DataBindingAdapter<T>(diffCallback: DiffUtil.ItemCallback<T> = defaultComparator()) :
    ListAdapter<T, DataBindingAdapter.ViewHolder<T>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) =
        holder.bind(getItem(position))

    class ViewHolder<T>(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        var bindingItem: T? = null

        fun bind(item: T) {
            bindingItem = item
        }
    }
}

fun <T> defaultComparator() = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(old: T, new: T): Boolean = old == new
    override fun areContentsTheSame(old: T, new: T): Boolean = old.hashCode() == new.hashCode()
}

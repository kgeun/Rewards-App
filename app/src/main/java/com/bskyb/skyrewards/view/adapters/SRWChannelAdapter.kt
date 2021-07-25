package com.bskyb.skyrewards.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.data.model.SRWChannel
import com.bskyb.skyrewards.databinding.ListitemChannelBinding
import com.bskyb.skyrewards.databinding.ListitemChannelHeaderBinding

class SRWChannelAdapter(val parentView: ViewGroup, val items: List<SRWChannel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val HEADER = 0
    val CONTENT = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HEADER) {
            object: RecyclerView.ViewHolder(
                ListitemChannelHeaderBinding.inflate(
                    LayoutInflater.from(parentView.context), parentView, false
                ).root
            ){}
        } else {
            ChannelHolder(
                ListitemChannelBinding.inflate(
                    LayoutInflater.from(parentView.context), parentView, false
                )
            )
        }
    }

    override fun getItemCount(): Int = items.size + 1

    override fun getItemViewType(position: Int): Int {
        return if (position == 0)
                HEADER
            else
                CONTENT
    }

    inner class ChannelHolder(
        private val binding: ListitemChannelBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SRWChannel) {
            binding.apply {
                channel = item
                root.setOnClickListener {
//                    findNavController(it).navigate(R.id.splash_to_start, null, null, FragmentNavigatorExtras(binding.topLogoImg to "app_logo"))
                    findNavController(it).navigate(R.id.channel_to_account)
                }
                executePendingBindings()
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position > 0) {
            (holder as ChannelHolder).bind(items[position - 1])
        }
    }
}
package com.bskyb.skyrewards.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bskyb.skyrewards.R
import com.bskyb.skyrewards.SRWApplication
import com.bskyb.skyrewards.analytics.SRWAnalytics
import com.bskyb.skyrewards.data.model.SRWChannel
import com.bskyb.skyrewards.databinding.ListitemChannelBinding
import com.bskyb.skyrewards.databinding.ListitemChannelHeaderBinding
import com.bskyb.skyrewards.view.SRWMainViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


class SRWChannelAdapter (val parentView: ViewGroup, val items: List<SRWChannel>, val myChannel: MutableLiveData<SRWChannel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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

    inner class ChannelHolder (
        private val binding: ListitemChannelBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SRWChannel) {
            binding.apply {
                channel = item
                cardView.isClickable = true
                cardView.isFocusable = true
                cardView.setOnClickListener {
                    val navBuilder = NavOptions.Builder()
                        .setEnterAnim(R.anim.slide_from_right)
                        .setExitAnim(R.anim.slide_to_left)
                        .setPopEnterAnim(R.anim.slide_from_left)
                        .setPopExitAnim(R.anim.slide_to_right)
                    findNavController(root).navigate(R.id.channel_to_account, null, navBuilder.build())
                    myChannel.postValue(item)
                    Toast.makeText(binding.root.context, "${item.channelTitle} has been selected.", Toast.LENGTH_SHORT).show()
                    SRWAnalytics.sendClick("ChannelBtn_${item.channelType}_${javaClass.simpleName}")
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
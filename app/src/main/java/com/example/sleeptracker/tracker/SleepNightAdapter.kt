package com.example.sleeptracker.tracker

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sleeptracker.R
import com.example.sleeptracker.convertDurationToFormatted
import com.example.sleeptracker.convertNumericQualityToString
import com.example.sleeptracker.database.SleepNight
import com.example.sleeptracker.databinding.TextItemViewBinding

class SleepNightAdapter : ListAdapter<SleepNight, TextItemViewHolder>(SleepNightDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        return TextItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class TextItemViewHolder private constructor(val dataBinding: TextItemViewBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {
    val res: Resources = dataBinding.root.resources

    fun bind(item: SleepNight) {
        dataBinding.sleepLength.text =
            convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
        dataBinding.sleepQuality.text = convertNumericQualityToString(item.sleepQuality, res)
        dataBinding.qualityImage.setImageResource(when (item.sleepQuality) {
            0 -> R.drawable.ic_sleep_0
            1 -> R.drawable.ic_sleep_1
            2 -> R.drawable.ic_sleep_2
            3 -> R.drawable.ic_sleep_3
            4 -> R.drawable.ic_sleep_4
            5 -> R.drawable.ic_sleep_5
            else -> R.drawable.ic_sleep_active
        })
    }

    companion object {
        fun from(parent: ViewGroup): TextItemViewHolder {
            val binding: TextItemViewBinding =
                TextItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return TextItemViewHolder(binding)
        }
    }
}

class SleepNightDiffCallback :
    DiffUtil.ItemCallback<SleepNight>() {
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem == newItem
    }
}
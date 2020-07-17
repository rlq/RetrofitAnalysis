package com.lq.he.sum.plist.ad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lq.he.sum.data.Plant
import com.lq.he.sum.databinding.ListItemPlantBinding
import com.lq.he.sum.home.HomeViewPagerFragmentDirections

// 植物目录的Adapter Adapter for the [RecyclerView] in [PlantListFragment].

class PlantAdapter: ListAdapter<Plant, RecyclerView.ViewHolder>(PlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlantViewHolder(
            ListItemPlantBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plant = getItem(position)
        (holder as PlantViewHolder).bind(plant)
    }

    class PlantViewHolder(
        private val binding: ListItemPlantBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {
                // let 是什么作用
                setClickListener {
                    plant?.let { plant ->
                        navigateToPlant(plant, it)
                    }
                }
            }
        }

        private fun navigateToPlant(plant: Plant, view: View) {
            val direction = HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(plant.plantId)
            view.findNavController().navigate(direction)
        }

        fun bind(item: Plant) {
            binding.apply {
                plant = item
                executePendingBindings()
            }
        }
    }

}

private class PlantDiffCallback: DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }
}
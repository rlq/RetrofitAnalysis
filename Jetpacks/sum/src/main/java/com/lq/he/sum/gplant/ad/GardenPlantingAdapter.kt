package com.lq.he.sum.gplant.ad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lq.he.sum.R
import com.lq.he.sum.data.PlantAndGardenPlantings
import com.lq.he.sum.databinding.ListItemGardenPlantingBinding
import com.lq.he.sum.home.HomeViewPagerFragmentDirections
import com.lq.he.sum.pad.vm.PlantAndGardenPlantingsViewModel
import java.lang.StringBuilder

// List + Adapter + ViewHolder
class GardenPlantingAdapter
    : ListAdapter<PlantAndGardenPlantings, GardenPlantingAdapter.ViewHolder>(
    GardenPlantDiffCallback()
) {

    // 实现ListAdapter的接口，想下Java是怎么实现的，方法类似
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_garden_planting, parent, false
            )
        )
    }

    // 绑定VH
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // 某个VH 接口，包含点击事件，导航事件，与VM绑定接口
    class ViewHolder (
        private val binding: ListItemGardenPlantingBinding
    ): RecyclerView.ViewHolder(binding.root) {

        // init 是什么情况
        init {
            // with 就是讲binding变量重复调用接口，[this. 就代表 binding.] 【this. 也可以省略，viewModel先的this.就省略了】
            // with 有返回值，
            with(binding) {
                this.setClickListener { view ->
                    viewModel?.plantId?.let { plantId ->
                        navigateToPlant(plantId, view)
                    }
                }
            }
        }

        // with有返回值的举例
        fun withReturn(): String {
            val stringBuilder = StringBuilder()
//            return with(stringBuilder) {
//                for (letter in 'A'..'Z') {
//                    this.append(letter)
//                }
//                append("\n now I know the aplhabet!")
//                // 返回lambda中最后一个表达式的值，不能返回一个接收者的对象(apply)
//                toString()
//            }
            // 使用apply,返回一个对象，也就是StringBuilder
            return stringBuilder.apply {
                for (letter in 'A'..'Z') {
                    append(letter)
                }
                append("\n now I know the aplhabet!")
            }.toString()
        }

        // 点击Garden某一个item，会跳转到详情页
        private fun navigateToPlant(plantId: String, view: View) {
            val direction = HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(plantId)
            // 找到的Navigation控制器，然后导航到相应页面，但direction其实就是一个fragment
            view.findNavController().navigate(direction)
        }

        fun bind(plantings: PlantAndGardenPlantings) {
            // with是什么作用，使用给定的[receiver]作为接收方调用指定的函数[block]并返回结果。
            with(binding) {
                this.viewModel =
                    PlantAndGardenPlantingsViewModel(
                        plantings
                    )
                // 执行绑定
                this.executePendingBindings()
            }
        }
    }
}

private class GardenPlantDiffCallback: DiffUtil.ItemCallback<PlantAndGardenPlantings>() {

    override fun areItemsTheSame(
        oldItem: PlantAndGardenPlantings,
        newItem: PlantAndGardenPlantings
    ): Boolean {
        return oldItem.plant.plantId == newItem.plant.plantId
    }

    override fun areContentsTheSame(
        oldItem: PlantAndGardenPlantings,
        newItem: PlantAndGardenPlantings
    ): Boolean {
        return oldItem.plant == newItem.plant
    }
}
package com.lq.he.sum

import androidx.fragment.app.Fragment
import com.lq.he.sum.data.Plant

// 使用Navigation跳转
class PlantDetailFragment: Fragment() {

//    private val args: PlantDetail

    interface Callback {
        fun add(plant: Plant?)
    }
}


package com.lq.he.sum.home.adapters


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lq.he.sum.gplant.GardenFragment
import com.lq.he.sum.plist.PlantListFragment
import java.lang.IndexOutOfBoundsException

// 全局不可写变量, 将全局变量写在类外，类名就会带有.kt
const val MY_GARDEN_PAGE_INDEX = 0
const val PLANT_LIST_PAGE_INDEX = 1

// 与HomeViewPagerFragment结合使用
class SunflowerPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    // 两个pager对应的fragment类
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        MY_GARDEN_PAGE_INDEX to { GardenFragment() },
        PLANT_LIST_PAGE_INDEX to { PlantListFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}
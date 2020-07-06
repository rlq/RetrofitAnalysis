package com.lq.he.sum.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.lq.he.sum.R
import com.lq.he.sum.home.adapters.MY_GARDEN_PAGE_INDEX
import com.lq.he.sum.home.adapters.PLANT_LIST_PAGE_INDEX
import com.lq.he.sum.home.adapters.SunflowerPagerAdapter
import com.lq.he.sum.databinding.FragmentViewPagerBinding
import java.lang.IndexOutOfBoundsException

// 首页包含 我的花园种植的植物 + 植物目录
class HomeViewPagerFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        // 1个HomeFragment 内包含 GardenFragment + PlantListFragment
        viewPager.adapter = SunflowerPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) {
            tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        // 类似于switch case
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> R.drawable.garden_tab_selector
            PLANT_LIST_PAGE_INDEX -> R.drawable.plant_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> getString(R.string.my_garden_title)
            PLANT_LIST_PAGE_INDEX -> getString(R.string.plant_list_title)
            else -> ""
        }
    }
}
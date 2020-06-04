package com.lq.he.sum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lq.he.sum.adapters.MY_GARDEN_PAGE_INDEX
import com.lq.he.sum.adapters.PLANT_LIST_PAGE_INDEX
import java.lang.IndexOutOfBoundsException

class HomeViewPagerFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // val binding = FragmentViewPagerBinding

        return super.onCreateView(inflater, container, savedInstanceState)
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
            PLANT_LIST_PAGE_INDEX -> getString(R.string.plant_details_title)
            else -> ""
        }
    }
}
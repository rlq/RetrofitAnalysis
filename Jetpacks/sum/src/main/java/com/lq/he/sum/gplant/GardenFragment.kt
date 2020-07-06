package com.lq.he.sum.gplant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.lq.he.sum.GardenPlantManager
import com.lq.he.sum.R
import com.lq.he.sum.gplant.ad.GardenPlantingAdapter
import com.lq.he.sum.home.adapters.PLANT_LIST_PAGE_INDEX
import com.lq.he.sum.databinding.FragmentGardenBinding
import com.lq.he.sum.gplant.vm.GardenPlantingListViewModel

/**
 * 我的花园的Fragment
 * VM + observe
 * LiveData
 * binding
 * adapter
 */
class GardenFragment : Fragment() {

    // suspend
    private lateinit var binding: FragmentGardenBinding

    // 加载数据
    private val viewModel: GardenPlantingListViewModel by viewModels {
        GardenPlantManager.provideGardenPlantingListVieModelFactory(
            requireContext()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGardenBinding.inflate(inflater, container, false)
        val adapter = GardenPlantingAdapter()
        binding.gardenList.adapter = adapter
        binding.addPlant.setOnClickListener {
            navigateToPlantListPage()
        }
        subscribeUI(adapter, binding)
        return binding.root
    }

    // 添加植物，点击跳转到植物目录页，然后打开详情点击+
    private fun navigateToPlantListPage() {
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem = PLANT_LIST_PAGE_INDEX
    }

    private fun subscribeUI(adapter: GardenPlantingAdapter, binding: FragmentGardenBinding) {
        viewModel.plantAndGardenPlantings.observe(viewLifecycleOwner) { result ->
            binding.hasPlantings = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
    }
}
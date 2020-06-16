package com.lq.he.sum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.lq.he.sum.adapters.GardenPlantingAdapter
import com.lq.he.sum.adapters.PLANT_LIST_PAGE_INDEX
import com.lq.he.sum.databinding.FragmentGardenBinding
import com.lq.he.sum.viewmodels.GardenPlantingListViewModel

/**
 * VM + observe
 * LiveData
 * binding
 * adapter
 */
class GardenFragment : Fragment() {

    // suspend
    private lateinit var binding: FragmentGardenBinding

    private val viewModel: GardenPlantingListViewModel by viewModels {
        GardenPlantManager.provideGardenPlantingListVieModelFactory(requireContext())
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

    // 点击跳转到下一页
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
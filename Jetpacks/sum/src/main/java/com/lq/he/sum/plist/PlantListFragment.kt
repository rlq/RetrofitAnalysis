package com.lq.he.sum.plist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.lq.he.sum.GardenPlantManager
import com.lq.he.sum.R
import com.lq.he.sum.plist.ad.PlantAdapter
import com.lq.he.sum.databinding.FragmentPlantListBinding
import com.lq.he.sum.plist.vm.PlantListViewModel

// 植物目录 listFragment UI
class PlantListFragment : Fragment() {

    // 从工厂中获取VM然后获取到LiveData的数据
    private val viewModel: PlantListViewModel by viewModels {
        GardenPlantManager.providePlantListViewModelFactory(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate UI
        val binding = FragmentPlantListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = PlantAdapter()
        // 将生成的adapter赋值给listView的adapter
        binding.plantList.adapter = adapter
        subscribeUI(adapter)
        return binding.root
    }

    private fun subscribeUI(adapter: PlantAdapter) {
        viewModel.plants.observe(viewLifecycleOwner) { plants ->
            adapter.submitList(plants)
        }
    }

    // 创建菜单
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_plant_list, menu)
    }

    // 点击菜单某项
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_zone -> {
                updateData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateData() {
        with (viewModel) {
            if (isFiltered()) {
                clearGrowZoneNumber()
            } else {
                setGrowZoneNumber(9)
            }
        }
    }


}
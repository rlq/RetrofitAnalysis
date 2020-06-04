package com.lq.he.sum

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lq.he.sum.viewmodels.GardenPlantingListViewModel

/**
 * VM + observe
 * LiveData
 * binding
 * adapter
 */
class GardenFragment : Fragment() {

    // susper
//    private lateinit var binding: FragmentGardenBinding

    private val viewModel: GardenPlantingListViewModel by viewModels {
        // context = requireContext()
        GardenPlantManager.provideGardenPlantingListVieModelFactory(requireContext())
    }
}
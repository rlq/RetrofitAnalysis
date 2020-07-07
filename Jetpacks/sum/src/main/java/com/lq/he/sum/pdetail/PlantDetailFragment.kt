package com.lq.he.sum.pdetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ShareCompat
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.lq.he.sum.GardenPlantManager
import com.lq.he.sum.R
import com.lq.he.sum.data.Plant
import com.lq.he.sum.databinding.FragmentPlantDetailBinding
import com.lq.he.sum.pdetail.vm.PlantDetailViewModel

/**
 * 使用Navigation跳转
 * 这一页UI：< 分享；image banner； + FB ；detail des
 */
class PlantDetailFragment: Fragment() {

    private val args: PlantDetailFragmentArgs by navArgs()

    // 获取植物详情的VM
    private val plantDetailViewModel: PlantDetailViewModel by viewModels {
        GardenPlantManager.providePlantDetailViewModelFactory(requireContext(), args.plantId)
    }

    private fun hideAppBarFab(fab: FloatingActionButton) {
        val params = fab.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as FloatingActionButton.Behavior
        // 关闭自动隐藏
        behavior.isAutoHideEnabled = false
        fab.hide()
    }


    @Suppress("DEPRECATION")
    private fun createShareIntent() {
        val shareText = plantDetailViewModel.plant.value.let { plant ->
            if (plant == null) {
                ""
            } else {
                getString(R.string.share_text_plant, plant.name)
            }
        }
        val shareIntent = activity?.let {
            ShareCompat.IntentBuilder.from(it)
                .setText(shareText)
                .setType("text/plain")
                .createChooserIntent()
                .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        }
        startActivity(shareIntent)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPlantDetailBinding>(
            inflater, R.layout.fragment_plant_detail, container, false
        ).apply {
            // apply 与 with的区别
            viewModel = plantDetailViewModel
            lifecycleOwner = viewLifecycleOwner
            // 创建1个Add的callback，然后实现
            callback = object : Callback {
                override fun add(plant: Plant?) {
                    plant?.let {
                        hideAppBarFab(fab)
                        plantDetailViewModel.addPlantToGarden()
                        Snackbar.make(root, R.string.added_plant_to_garden, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
            var isToolbarShown = false

            // scroll change listener begins at Y = 0 when image is fully collapsed
            plantDetailScrollview.setOnScrollChangeListener(
                NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

                    // User scrolled past image to height of toolbar and the title text is
                    // underneath the toolbar, so the toolbar should be shown.
                    val shouldShowToolbar = scrollY > toolbar.height

                    // The new state of the toolbar differs from the previous state; update
                    // appbar and toolbar attributes.
                    if (isToolbarShown != shouldShowToolbar) {
                        isToolbarShown = shouldShowToolbar

                        // Use shadow animator to add elevation if toolbar is shown
                        appbar.isActivated = shouldShowToolbar

                        // Show the plant name if toolbar is shown
                        toolbarLayout.isTitleEnabled = shouldShowToolbar
                    }
                }
            )

            toolbar.setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }

            toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_share -> {
                        createShareIntent()
                        true
                    }
                    else -> false
                }
            }
        }
        setHasOptionsMenu(true)

        return binding.root
    }

    // 在layout中点击FB，调用callback的add接口
    interface Callback {
        fun add(plant: Plant?)
    }
}


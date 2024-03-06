package com.example.android5_8.ui.home.bottomSheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.android5_8.R
import com.example.android5_8.WeatherViewModel
import com.example.android5_8.databinding.HomeBottomSheetBinding
import com.example.android5_8.ui.home.bottomSheet.recyclerview.WeatherAdapter
import com.example.android5_8.ui.search.SearchFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheetDialog(callback: BottomSheetListener) : BottomSheetDialogFragment() {
    private lateinit var binding: HomeBottomSheetBinding
    private val viewModel: WeatherViewModel by activityViewModels()
    lateinit var adapter: WeatherAdapter

    private var bottomSheetListener: BottomSheetListener? = null

    init {
        this.bottomSheetListener = callback
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // used to show the bottom sheet dialog
        dialog?.setOnShowListener { it ->
            val d = it as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
        initClickers()
    }

    private fun initClickers() {
        binding.btnSearch.setOnClickListener {
            bottomSheetListener?.onBottomSheetNavigationPress()
        }

    }

    private fun observe() {
        viewModel.weatherLiveData.observe(viewLifecycleOwner) {
            adapter = WeatherAdapter(it.list)
            binding.rvBottomSheet.adapter = adapter
        }
    }
    override fun onDestroyView() {
        if (view != null) {
            val parentViewGroup = requireView().parent as ViewGroup?
            parentViewGroup?.removeAllViews();
        }
        super.onDestroyView()
    }

    companion object {
        const val TAG = "ModalBottomSheetDialog"
    }
}
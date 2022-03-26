package ru.barinov.drivershelper.ui

import android.os.Bundle
import android.view.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import ru.barinov.drivershelper.R
import ru.barinov.drivershelper.databinding.ProfileBottomSheetLayoutBinding
import ru.barinov.drivershelper.ui.recyclerViews.ProfilesRecyclerViewAdapter

class ProfileListBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var binding: ProfileBottomSheetLayoutBinding
    private lateinit var profilesRecyclerView: RecyclerView
    private  val adapter =ProfilesRecyclerViewAdapter()
    private val viewModel by sharedViewModel<StatisticFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = ProfileBottomSheetLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initViews()
        createListeners()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun createListeners() {
        lifecycleScope.launchWhenStarted {
            withContext(Dispatchers.Main) {
                viewModel.profilesData.onEach { itemList ->
                        adapter.items = itemList
                            if (itemList.isNotEmpty()) {
                                binding.profileBottomSheetDialogPlaceholderImgView.visibility = View.GONE
                                binding.profileBottomSheetDialogPlaceholderTitle.visibility = View.GONE
                            }

                    }.collect()
            }
        }

        lifecycleScope.launchWhenCreated {
            withContext(Dispatchers.Main){
                viewModel.profileSelected.onEach { event ->
                    event.getContentIfNotHandled()?.let { isChecked ->
                        if (isChecked) {
                            this@ProfileListBottomSheetDialog.dismiss()
                        }
                    }
                }
                }.collect()
            }
        }


    private fun initViews() {
        profilesRecyclerView = binding.profilesRecyclerView
        profilesRecyclerView.adapter = adapter
        binding.addProfileFab.setOnClickListener {
            findNavController().navigate(R.id.action_profileListBottomSheetDialog_to_profileCreationFragment)
            this.dismiss()
        }
    }
}
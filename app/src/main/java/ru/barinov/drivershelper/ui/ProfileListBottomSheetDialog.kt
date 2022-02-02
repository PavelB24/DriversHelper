package ru.barinov.drivershelper.ui

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.barinov.drivershelper.databinding.ProfileBottomSheetLayoutBinding

class ProfileListBottomSheetDialog: BottomSheetDialogFragment() {

    private lateinit var binding: ProfileBottomSheetLayoutBinding
    private lateinit var profilesRecyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileBottomSheetLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initViews()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews() {
        profilesRecyclerView= binding.profilesRecyclerView
        binding.addProfileFab.setOnClickListener {
            (requireActivity() as ActivityMain).openProfileCreationFragment()
            this.dismiss()
        }
    }
}
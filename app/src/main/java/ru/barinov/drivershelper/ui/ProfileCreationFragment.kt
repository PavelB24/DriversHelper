package ru.barinov.drivershelper.ui

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import ru.barinov.drivershelper.databinding.*
import ru.barinov.drivershelper.domain.models.*
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.RuntimeException

class ProfileCreationFragment: Fragment() {

    private lateinit var binding: ProfileCreationLayoutBinding
    private lateinit var profileSpinner: Spinner
    private lateinit var fuelSpinner: Spinner
    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>
    private lateinit var profileImage: ShapeableImageView
    private var profileImageAsByteArray: ByteArray? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileCreationLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initSpinners()

        profileImage=   binding.profileImageView

        registerForImageSelectionResults()

        profileImage.setOnClickListener {
            selectImageFromGallery()
        }

        binding.createButton.setOnClickListener {

            viewModel.createProfile(ProfileDraft(
                profileImage= profileImageAsByteArray,
                userName = binding.userNameTextView.text.toString(),
                profileType = profileSpinner.selectedItem as ProfileType,
                fuelType = fuelSpinner.selectedItem as FuelType,
                fuelConsume = binding.fuelConsumeEditText.text.toString(),
                depreciationOfMaintenance = binding.depreciationOfMaintenanceEditText.text.toString()
            ))
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun registerForImageSelectionResults() {
        galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val returnUri: Uri = result.data?.data!!
                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, returnUri)
                        profileImage.setImageBitmap(bitmap)
                        //Todo TO VM
                       viewModel.bitmapToByteArray(bitmap)
                        binding.rotateLeftImageButton.visibility = View.VISIBLE
                        binding.rotateRightImageButton.visibility = View.VISIBLE

                    }
                    catch (exception: RuntimeException) {
                        Toast.makeText(
                            requireContext(), "00000", Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                }

            }


    private fun selectImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        galleryLauncher.launch(intent)
    }

    private fun initSpinners() {
         profileSpinner= binding.profileTypeSpinner
         fuelSpinner = binding.fuelTypeSpinner

        val profileSpinnerAdapter = ArrayAdapter<ProfileType>(requireContext(), android.R.layout.simple_spinner_item, ProfileType.values())
        val fuelSpinnerAdapter = ArrayAdapter<FuelType>(requireContext(), android.R.layout.simple_spinner_item, FuelType.values())

        profileSpinner.adapter =profileSpinnerAdapter
        fuelSpinner.adapter = fuelSpinnerAdapter

    }
}
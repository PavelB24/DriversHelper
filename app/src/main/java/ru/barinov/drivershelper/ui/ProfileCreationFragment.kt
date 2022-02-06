package ru.barinov.drivershelper.ui

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import ru.barinov.drivershelper.databinding.*
import ru.barinov.drivershelper.domain.models.*
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.drawToBitmap
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.*
import java.lang.RuntimeException

private const val rotateLeftDegrees = -90f
private const val rotateRightDegrees = 90f

class ProfileCreationFragment : Fragment() {

    private lateinit var binding: ProfileCreationLayoutBinding
    private lateinit var profileSpinner: Spinner
    private lateinit var fuelSpinner: Spinner
    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>
    private lateinit var profileImage: ShapeableImageView
    private var profileImageAsByteArray: ByteArray? = null
    private val viewModel by viewModel<ProfileCreationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = ProfileCreationLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initUi()


        lifecycleScope.launchWhenCreated {
            viewModel.imageArray.onEach { value ->
                profileImageAsByteArray = value
            }.collect()
        }

        registerForImageSelectionResults()

        profileImage.setOnClickListener {
            selectImageFromGallery()
        }

        binding.createButton.setOnClickListener {

            if (checkOnNecessaryFields()) {

                viewModel.createProfile(
                    ProfileDraft(
                        profileImage = profileImageAsByteArray,
                        userName = binding.userNameTextView.text.toString(),
                        profileType = profileSpinner.selectedItem as ProfileType,
                        fuelType = fuelSpinner.selectedItem as FuelType,
                        fuelConsume = binding.fuelConsumeEditText.text.toString(),
                        depreciationOfMaintenance = binding.depreciationOfMaintenanceEditText.text.toString()
                    )
                )
                findNavController().popBackStack()

            } else{
                Toast.makeText(requireContext(), "Not all necessary fields filled", Toast.LENGTH_SHORT).show()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun checkOnNecessaryFields(): Boolean {
        return (binding.userNameTextView.text.isNotEmpty()
            && binding.fuelConsumeEditText.text!!.isNotEmpty()
            && binding.depreciationOfMaintenanceEditText.text!!.isNotEmpty())
    }

    private fun initUi() {
        initSpinners()

        profileImage = binding.profileImageView

        binding.rotateLeftImageButton.setOnClickListener {

            rotateImageOn90Degree(rotateLeftDegrees)

        }


        binding.rotateRightImageButton.setOnClickListener {

            rotateImageOn90Degree(rotateRightDegrees)
        }
    }

    private fun rotateImageOn90Degree(degrees: Float) {
        val matrix = Matrix()
        matrix.postRotate(profileImage.rotation + degrees)
        profileImage.rotation = (profileImage.rotation + degrees)
        val idleBitmap = profileImage.drawToBitmap()
        lifecycleScope.launch(Dispatchers.Default) {
            val rotatedImgBitmap =
                Bitmap.createBitmap(idleBitmap, 0, 0, idleBitmap.width, idleBitmap.height, matrix, true)
            viewModel.bitmapToByteArray(rotatedImgBitmap)
        }
    }

    private fun registerForImageSelectionResults() {
        galleryLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val returnUri: Uri = result.data?.data!!
                    try {
                        val bitmap =
                            MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, returnUri)
                        profileImage.setImageBitmap(bitmap)
                        viewModel.bitmapToByteArray(bitmap)
                        binding.rotateLeftImageButton.visibility = View.VISIBLE
                        binding.rotateRightImageButton.visibility = View.VISIBLE

                    } catch (exception: RuntimeException) {
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
        profileSpinner = binding.profileTypeSpinner
        fuelSpinner = binding.fuelTypeSpinner

        val profileSpinnerAdapter = ArrayAdapter<ProfileType>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            ProfileType.values()
        )
        val fuelSpinnerAdapter =
            ArrayAdapter<FuelType>(requireContext(), android.R.layout.simple_spinner_item, FuelType.values())

        profileSpinner.adapter = profileSpinnerAdapter
        fuelSpinner.adapter = fuelSpinnerAdapter

    }
}
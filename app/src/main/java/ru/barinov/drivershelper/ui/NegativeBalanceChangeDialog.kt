package ru.barinov.drivershelper.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.barinov.drivershelper.databinding.*
import ru.barinov.drivershelper.domain.models.*
import java.lang.IllegalStateException

class NegativeBalanceChangeDialog : DialogFragment() {

    private lateinit var binding: BalanceChangeDialogLayoutBinding
    private var spendingCategory: SpendingCategory? = null
    private val viewModel by viewModel<BalanceChangeDialogViewModel>()

    private val profileId: String by lazy {
        val id = requireArguments().getString(StatisticFragment.SHARED_PREFS_AND_ARGS_KEY)
        if (id == null) {
            throw IllegalStateException("ID should be provided")
        }
        return@lazy id
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = BalanceChangeDialogLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews() {
        with(binding){
            fuelImageButton.setOnClickListener { spendingCategory = SpendingCategory.FUEL }
            sparePartsImageButton.setOnClickListener { spendingCategory = SpendingCategory.SPARE_PARTS }
            foodImageButton.setOnClickListener { spendingCategory = SpendingCategory.FOOD }
            repairImageButton.setOnClickListener { spendingCategory = SpendingCategory.SERVICE }
            taxImageButton.setOnClickListener { spendingCategory = SpendingCategory.TAX }
            evacuationImageButton.setOnClickListener { spendingCategory = SpendingCategory.EVACUATION }
            fineImageButton.setOnClickListener { spendingCategory = SpendingCategory.FINE }
            otherImageButton.setOnClickListener { spendingCategory = SpendingCategory.OTHER }
            submitButton.setOnClickListener {
                if (binding.valueConsumeEditText.text.toString().isNotEmpty()
                    && spendingCategory!= null ){
                        val value = binding.valueConsumeEditText.text.toString().toFloat()
                        viewModel.addMovement(AccountMoveDraft(profileId, -value, MovementType.EXPENSE, spendingCategory!! ))
                    dismiss()
                } else {
                    Toast.makeText(requireContext(),
                        "Value is empty or spending spending category is not chosen",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
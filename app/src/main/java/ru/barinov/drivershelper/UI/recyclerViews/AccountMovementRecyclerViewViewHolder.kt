package ru.barinov.drivershelper.UI.recyclerViews

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.barinov.drivershelper.R
import ru.barinov.drivershelper.databinding.ItemAccountMoveBinding

class AccountMovementRecyclerViewViewHolder(
    viewHolderBinding: ItemAccountMoveBinding
) : RecyclerView.ViewHolder(viewHolderBinding.root) {

    val movementCategoryTextView = viewHolderBinding.movementCategoryTextView
    val dateOfEventTimeTextView = viewHolderBinding.dateOfEventTextView
    val movementValueTextView = viewHolderBinding.valueTextView

}
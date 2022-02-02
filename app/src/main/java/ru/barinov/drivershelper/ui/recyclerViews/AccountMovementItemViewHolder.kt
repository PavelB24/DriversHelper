package ru.barinov.drivershelper.ui.recyclerViews

import androidx.recyclerview.widget.RecyclerView
import ru.barinov.drivershelper.databinding.ItemAccountMoveBinding

class AccountMovementItemViewHolder(
    viewHolderBinding: ItemAccountMoveBinding
) : RecyclerView.ViewHolder(viewHolderBinding.root) {

    val movementCategoryTextView = viewHolderBinding.movementCategoryTextView
    val dateOfEventTimeTextView = viewHolderBinding.dateOfEventTextView
    val movementValueTextView = viewHolderBinding.valueTextView

}
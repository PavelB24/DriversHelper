package ru.barinov.drivershelper.ui.recyclerViews

import androidx.recyclerview.widget.RecyclerView
import ru.barinov.drivershelper.databinding.ItemeProfileBinding

class ProfileItemViewHolder(
    binding: ItemeProfileBinding
): RecyclerView.ViewHolder(binding.root) {

    val profileImage = binding.itemProfileImgView
    val profileType = binding.profileTypeName
    val profilesName = binding.profileUserName
}
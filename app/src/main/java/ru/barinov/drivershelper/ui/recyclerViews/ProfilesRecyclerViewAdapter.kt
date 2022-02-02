package ru.barinov.drivershelper.ui.recyclerViews

import android.graphics.BitmapFactory
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import ru.barinov.drivershelper.databinding.*
import ru.barinov.drivershelper.domain.models.ProfileEntity

class ProfilesRecyclerViewAdapter : RecyclerView.Adapter<ProfileItemViewHolder>() {

    var items = emptyList<ProfileRecyclerItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileItemViewHolder {
        val viewHolderBinding: ItemeProfileBinding =
            ItemeProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileItemViewHolder(viewHolderBinding)
    }

    override fun onBindViewHolder(holder: ProfileItemViewHolder, position: Int) {
        val item = getItem(position)

        if(item.image!= null){
            holder.profileImage.setImageBitmap(item.image)}

            holder.profileImage
        }



    private fun getItem(position: Int): ProfileRecyclerItem {

        return items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
package ru.barinov.drivershelper.UI.recyclerViews

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import ru.barinov.drivershelper.R
import ru.barinov.drivershelper.databinding.ItemAccountMoveBinding
import ru.barinov.drivershelper.domain.models.AccountMoveEntity

class AccountMovementsRecyclerViewAdapter: RecyclerView.Adapter<AccountMovementRecyclerViewViewHolder>() {

   private var items = emptyList<AccountMoveEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountMovementRecyclerViewViewHolder {
        val viewHolderBinding: ItemAccountMoveBinding =
            ItemAccountMoveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccountMovementRecyclerViewViewHolder(viewHolderBinding)
    }

    override fun onBindViewHolder(holder: AccountMovementRecyclerViewViewHolder, position: Int) {
        val item = getItem(position)

        holder.movementCategoryTextView.text = item.category.name
        holder.movementValueTextView.text= item.value.toString()
        holder.dateOfEventTimeTextView//todo
    }

    private fun getItem(position: Int): AccountMoveEntity {
        return items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }


    fun setItems(items: List<AccountMoveEntity>){
        this.items= items
    }
}
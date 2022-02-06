package ru.barinov.drivershelper.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import ru.barinov.drivershelper.databinding.*

class EmptyFragment: Fragment() {

    private lateinit var binding: EmptyLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EmptyLayoutBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
}
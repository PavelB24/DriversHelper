package ru.barinov.drivershelper.UI

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import ru.barinov.drivershelper.databinding.*

class ProfileCreationFragment: Fragment() {

    private lateinit var binding: ProfileCreationLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileCreationLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }
}
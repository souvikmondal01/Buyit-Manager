package com.buyit.buyitmanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.buyit.buyitmanager.R
import com.buyit.buyitmanager.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

//        binding.cvBackArrow.setOnClickListener {
//            requireActivity().finish()
//        }

//        DynamicColors.applyToActivitiesIfAvailable(requireActivity().application)

        binding.btnManageShop.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_manageShopFragment)
        }
        binding.btnManageCategory.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_manageCategoryFragment)
        }
//        binding.btn.setOnClickListener {
//            startActivity(Intent(requireActivity(), ComposeActivity::class.java))
//        }
        return binding.root
    }

}
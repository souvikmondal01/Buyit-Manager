package com.buyit.buyitmanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.buyit.buyitmanager.data.repositories.TempClick
import com.buyit.buyitmanager.databinding.FragmentCategoryBottomSheetDialogBinding
import com.buyit.buyitmanager.viewmodels.CategoryViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryBottomSheetFragment(val l: TempClick) : BottomSheetDialogFragment() {

    private var _binding: FragmentCategoryBottomSheetDialogBinding? = null
    private val binding get() = _binding!!
    private val db = Firebase.firestore
    val viewModel: CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBottomSheetDialogBinding.inflate(inflater, container, false)

        l.viewControl(binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val dbRef = db.collection("more").document("shopCategory").collection("list")
//        val id = dbRef.document().id
//        binding.btnAdd.setOnClickListener {
//            viewModel.addCategory(CategoryModel(id, binding.etCategory.text.toString().trim(), ""))
//        }
//
//        viewModel.addCategory.observe(viewLifecycleOwner) { state ->
//            when (state) {
//                is UiState.Loading -> {
//                    Log.e("Souvik", "Loading...")
//                    binding.pb.hide()
//                }
//                is UiState.Success -> {
//                    state.data.forEach {
//                        Log.e("Souvik", it.toString())
//                    }
//
//                    binding.pb.hide()
//                    toast(state.data)
//
//                }
//                is UiState.Failure -> {
//                    Log.e("Souvik", state.error.toString())
//                    binding.pb.hide()
//                    toast(state.error.toString())
//                }
//            }
//        }


    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "BottomSheet"
    }

}
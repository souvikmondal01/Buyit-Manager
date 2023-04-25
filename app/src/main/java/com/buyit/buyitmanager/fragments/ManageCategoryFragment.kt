package com.buyit.buyitmanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.buyit.buyitmanager.R
import com.buyit.buyitmanager.adapters.CategoryAdapter
import com.buyit.buyitmanager.data.repositories.TempClick
import com.buyit.buyitmanager.databinding.FragmentCategoryBottomSheetDialogBinding
import com.buyit.buyitmanager.databinding.FragmentManageCategoryBinding
import com.buyit.buyitmanager.models.CategoryModel
import com.buyit.buyitmanager.utils.Utils.db
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageCategoryFragment : Fragment(), TempClick {

    private lateinit var adapter: CategoryAdapter
    private val bottomSheetDialog = CategoryBottomSheetFragment(this)

    //    private val viewModel: CategoryViewModel by viewModels()
    private lateinit var binding: FragmentManageCategoryBinding
//    var deletePosition: Int = -1
//
//    private val adapter2 by lazy {
//        CategoryAdapter2(onItemClicked = { pos, item ->
//
//        }, onDeleteClicked = { pos, item ->
//            deletePosition = pos
//            viewModel.deleteCategory(item)
//        })
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentManageCategoryBinding.inflate(inflater, container, false)

        binding.cvBackArrow.setOnClickListener {
            it.findNavController().navigate(R.id.action_manageCategoryFragment_to_homeFragment)
        }


        binding.fab.setOnClickListener {
            bottomSheetDialog.show(childFragmentManager, CategoryBottomSheetFragment.TAG)
        }

        val collection = db.collection("more").document("shopCategory").collection("list")
        val recyclerOptions = FirestoreRecyclerOptions.Builder<CategoryModel>()
            .setQuery(collection, CategoryModel::class.java).build()
        adapter = CategoryAdapter(recyclerOptions)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter.startListening()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.recyclerView.adapter = adapter2
//        binding.recyclerView.layoutManager = LinearLayoutManager(context)
//        viewModel.getCategory()
//        viewModel.category.observe(viewLifecycleOwner) { state ->
//            when (state) {
//                is UiState.Loading -> {
//                    Log.e("Souvik", "Loading...")
//                    binding.pb.show()
//                }
//                is UiState.Success -> {
//                    state.data.forEach {
//                        Log.e("Souvik", it.toString())
//                    }
//
//                    binding.pb.hide()
//                    adapter2.updateList(state.data.toMutableList())
//
//                }
//                is UiState.Failure -> {
//                    Log.e("Souvik", state.error.toString())
//                    binding.pb.hide()
//                    toast(state.error.toString())
//                }
//            }
//        }

//        viewModel.deleteCategory.observe(viewLifecycleOwner) { state ->
//            when (state) {
//                is UiState.Loading -> {
//                    Log.e("Souvik", "Loading...")
//                    binding.pb.show()
//                }
//                is UiState.Success -> {
//                    state.data.forEach {
//                        Log.e("Souvik", it.toString())
//                    }
//
//                    binding.pb.hide()
//                    toast(state.data)
//                    if (deletePosition != -1) {
//                        adapter2.removeItem(deletePosition)
//                    }
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


    override fun viewControl(binding: FragmentCategoryBottomSheetDialogBinding) {
        binding.btnAdd.setOnClickListener {
            addCategory(binding.etCategory, binding.etCategory.text.toString().trim(), "")
            bottomSheetDialog.dismiss()
        }
    }

    private fun addCategory(et: EditText, categoryName: String, url: String) {
        val dbRef = db.collection("more").document("shopCategory").collection("list")
        val id = dbRef.document().id
        val data = CategoryModel(id, categoryName, url)
        dbRef.document(id).set(data)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    et.text.clear()
                } else {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
            }
    }


}
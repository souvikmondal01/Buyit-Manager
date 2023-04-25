package com.buyit.buyitmanager.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buyit.buyitmanager.databinding.ListCategoryBinding
import com.buyit.buyitmanager.models.CategoryModel
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CategoryAdapter(option: FirestoreRecyclerOptions<CategoryModel>) :
    FirestoreRecyclerAdapter<CategoryModel, CategoryAdapter.ViewHolder>(option) {
    class ViewHolder(val binding: ListCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: CategoryModel) {
        holder.binding.tvCategoryName.text = model.categoryName
        holder.binding.ivDelete.setOnClickListener {
            val db = Firebase.firestore
            db.collection("more").document("shopCategory").collection("list")
                .document(model.id.toString()).delete()
        }
    }

}
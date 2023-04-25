package com.buyit.buyitmanager.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buyit.buyitmanager.databinding.ListShopBinding
import com.buyit.buyitmanager.models.ShopModel
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ShopAdapter(options: FirestoreRecyclerOptions<ShopModel>) :
    FirestoreRecyclerAdapter<ShopModel, ShopAdapter.ViewHolder>(options) {

    class ViewHolder(val binding: ListShopBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListShopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: ShopModel) {

        val db = Firebase.firestore
        holder.binding.tvShopName.text = model.shopName
        holder.binding.tvShopCategory.text = model.shopCategory
        holder.binding.tvIsShopVerified.text = "Verification: " + model.verificationStatus

        holder.binding.btnVerify.setOnClickListener {
            db.collection("shop").document(model.id.toString()).update("verificationStatus", "done")
                .addOnSuccessListener {}
                .addOnFailureListener {}

        }
        holder.binding.btnDelete.setOnClickListener {
            db.collection("shop").document(model.id.toString()).delete()
                .addOnSuccessListener {}
                .addOnFailureListener {}

        }
    }

}
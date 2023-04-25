package com.buyit.buyitmanager.data.repositories

import com.buyit.buyitmanager.models.CategoryModel
import com.buyit.buyitmanager.utils.UiState
import com.google.firebase.firestore.FirebaseFirestore

class CategoryRepositoryImp(private val db: FirebaseFirestore) : CategoryRepository {
    override fun getCategory(result: (UiState<List<CategoryModel>>) -> Unit) {
        db.collection("more").document("shopCategory")
            .collection("list").get().addOnSuccessListener {
                val categories = arrayListOf<CategoryModel>()
                for (document in it) {
                    val category = document.toObject(CategoryModel::class.java)
                    categories.add(category)
                }
                result.invoke(
                    UiState.Success(categories)
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(it.localizedMessage)
                )
            }
    }

    override fun addCategory(category: CategoryModel, result: (UiState<String>) -> Unit) {
        val docRef = db.collection("more").document("shopCategory")
            .collection("list").document()
        category.id = docRef.id
        docRef.set(category).addOnSuccessListener {
            result.invoke(
                UiState.Success("Category has been added successfully")
            )
        }.addOnFailureListener {
            result.invoke(
                UiState.Failure(it.localizedMessage)
            )
        }
    }

    override fun deleteCategory(category: CategoryModel, result: (UiState<String>) -> Unit) {
        val docRef = db.collection("more").document("shopCategory")
            .collection("list").document(category.id.toString())

        docRef.delete().addOnSuccessListener {
            result.invoke(
                UiState.Success("Category has been deleted successfully")
            )
        }.addOnFailureListener {
            result.invoke(
                UiState.Failure(it.localizedMessage)
            )
        }
    }
}
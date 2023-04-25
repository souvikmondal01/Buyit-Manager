package com.buyit.buyitmanager.data.repositories

import com.buyit.buyitmanager.models.CategoryModel
import com.buyit.buyitmanager.utils.UiState

interface CategoryRepository {

    fun getCategory(result: (UiState<List<CategoryModel>>) -> Unit)
    fun addCategory(category: CategoryModel, result: (UiState<String>) -> Unit)
    fun deleteCategory(category: CategoryModel, result: (UiState<String>) -> Unit)

}
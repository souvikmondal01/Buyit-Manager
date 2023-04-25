package com.buyit.buyitmanager.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buyit.buyitmanager.data.repositories.CategoryRepository
import com.buyit.buyitmanager.models.CategoryModel
import com.buyit.buyitmanager.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepository) :
    ViewModel() {

    private val _category = MutableLiveData<UiState<List<CategoryModel>>>()
    val category: LiveData<UiState<List<CategoryModel>>> get() = _category

    private val _addCategory = MutableLiveData<UiState<String>>()
    val addCategory: LiveData<UiState<String>> get() = _addCategory

    private val _deleteCategory = MutableLiveData<UiState<String>>()
    val deleteCategory: LiveData<UiState<String>> get() = _deleteCategory

    fun getCategory() {
        _category.value = UiState.Loading
        repository.getCategory { _category.value = it }
    }

    fun addCategory(category: CategoryModel) {
        _addCategory.value = UiState.Loading
        repository.addCategory(category) {
            _addCategory.value = it
        }
    }

    fun deleteCategory(category: CategoryModel) {
        _deleteCategory.value = UiState.Loading
        repository.deleteCategory(category) {
            _deleteCategory.value = it
        }
    }
}
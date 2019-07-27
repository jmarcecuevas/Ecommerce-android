package com.marcecuevas.easybuy.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marcecuevas.easybuy.data.repository.ProductRepository

class ProductViewModelFactory(
    private val productRepository: ProductRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun<T: ViewModel?> create(modelClass: Class<T>): T{
        return ProductViewModel(productRepository) as T
    }
}
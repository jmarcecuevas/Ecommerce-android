package com.marcecuevas.easybuy.view.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.marcecuevas.easybuy.R
import com.marcecuevas.easybuy.data.model.DTO.ProductDTO
import com.marcecuevas.easybuy.data.model.Error
import com.marcecuevas.easybuy.viewModel.ProductViewModel
import com.marcecuevas.easybuy.viewModel.ProductViewModelFactory
import org.kodein.di.generic.instance

class ProductsFragment: GenericFragment() {

    private val viewModelFactory: ProductViewModelFactory by instance()
    private lateinit var viewModel: ProductViewModel

    private lateinit var products: ProductDTO

    override fun layout(): Int {
        return R.layout.fragment_products
    }

    override fun init() {

        viewModel = ViewModelProviders.of(this,viewModelFactory).
            get(ProductViewModel::class.java)

        viewModel.productsLivedata.observe(this, Observer {
            this.products = it
        })

        viewModel.errorLiveData.observe(this, Observer {
            showError(Error(getString(R.string.error),it))
        })
    }

}
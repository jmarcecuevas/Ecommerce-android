package com.marcecuevas.easybuy.view.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcecuevas.easybuy.R
import com.marcecuevas.easybuy.data.model.DTO.ProductDTO
import com.marcecuevas.easybuy.data.model.Error
import com.marcecuevas.easybuy.view.adapter.ProductsAdapter
import com.marcecuevas.easybuy.viewModel.ProductViewModel
import com.marcecuevas.easybuy.viewModel.ProductViewModelFactory
import kotlinx.android.synthetic.main.fragment_products.*
import org.kodein.di.generic.instance

class ProductsFragment: GenericFragment() {

    private val viewModelFactory: ProductViewModelFactory by instance()
    private lateinit var viewModel: ProductViewModel

    private lateinit var products: ProductDTO

    override fun layout(): Int {
        return R.layout.fragment_products
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showProgress()
    }

    override fun init() {

        val adapter = ProductsAdapter(context) {
            navigateToProductDetail(it?.id)
        }
        productsRecyclerview.layoutManager = GridLayoutManager(context,2)
        productsRecyclerview.adapter = adapter

        viewModel = ViewModelProviders.of(this,viewModelFactory).
            get(ProductViewModel::class.java)

        viewModel.productsLivedata.observe(this, Observer {
            this.products = it
            adapter.loadItems(it.items)
            hideProgress()
        })

        viewModel.errorLiveData.observe(this, Observer {
            hideProgress()
            showError(Error(getString(R.string.error),it))
        })
    }

    private fun navigateToProductDetail(productID: String?){
        val directions = ProductsFragmentDirections.productDetailFragment(productID)
        findNavController().navigate(directions)
    }
}
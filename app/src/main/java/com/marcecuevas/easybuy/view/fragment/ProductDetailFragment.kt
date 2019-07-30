package com.marcecuevas.easybuy.view.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcecuevas.easybuy.R
import com.marcecuevas.easybuy.data.model.DTO.ProductDetailDTO
import com.marcecuevas.easybuy.data.model.DTO.ReviewDTO
import com.marcecuevas.easybuy.viewModel.ProductViewModel
import com.marcecuevas.easybuy.viewModel.ProductViewModelFactory
import kotlinx.android.synthetic.main.fragment_product_detail.*
import org.kodein.di.generic.instance
import com.marcecuevas.easybuy.data.model.Error
import com.marcecuevas.easybuy.view.adapter.ReviewsAdapter
import com.marcecuevas.easybuy.view.adapter.SliderViewPager


class ProductDetailFragment: GenericFragment(){

    private var productID: String? = null
    private val viewModelFactory: ProductViewModelFactory by instance()
    private lateinit var viewModel: ProductViewModel

    private var reviews: ReviewDTO? = null

    override fun layout(): Int = R.layout.fragment_product_detail

    override fun init() {
        showProgress()

        getArgs()

        startObserving()

        detailContainer.onClick = {
            navigateToReviews(reviews)
        }

        seeAllReviewsContainer.setOnClickListener(){
            navigateToReviews(reviews)
        }
    }

    private fun getArgs() {
        productID = arguments?.let {
            ProductDetailFragmentArgs.fromBundle(it).productId }
    }

    private fun startObserving() {
        viewModel = ViewModelProviders.of(this,viewModelFactory).
            get(ProductViewModel::class.java)

        productID?.let {
            viewModel.getProductDetail(it)
            viewModel.getReviewsFromProduct(it)
        }

        viewModel.productDetailLiveData.observe(this, Observer {
            it?.let {
                setupView(it)
            }
        })

        viewModel.productReviewsLiveData.observe(this, Observer {
            this.reviews = it
            it?.let {
                detailContainer.setupReviews(it)

                headerReviews.setupReviews(it)

                setupAdapter()
            }
        })

        viewModel.errorLiveData.observe(this, Observer {
            showError(Error(getString(R.string.error),it))
        })
    }

    private fun navigateToReviews(reviews: ReviewDTO?){
        val directions = ProductDetailFragmentDirections.reviewsFragment(reviews)
        findNavController().navigate(directions)
    }

    private fun setupAdapter(){
        val adapter = ReviewsAdapter(context)
        reviewsRV.layoutManager = LinearLayoutManager(context)
        reviewsRV.adapter = adapter
        reviewsRV.isNestedScrollingEnabled = false
        adapter.loadItems(reviews?.items?.first()?.reviews?.subList(0,3))
    }

    private fun setupView(item: ProductDetailDTO){
        with(item){
            context?.let {
                val adapter = SliderViewPager(it,resources.images)
                viewPager.adapter = adapter

                pageIndicator.attachToViewPager(viewPager)
            }

            detailContainer.setup(this)
        }
        hideProgress()
    }
}
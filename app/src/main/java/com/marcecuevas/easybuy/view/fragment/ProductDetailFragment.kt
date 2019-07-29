package com.marcecuevas.easybuy.view.fragment

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.marcecuevas.easybuy.R
import com.marcecuevas.easybuy.data.model.DTO.ProductDetailDTO
import com.marcecuevas.easybuy.data.model.DTO.ReviewDTO
import com.marcecuevas.easybuy.viewModel.ProductViewModel
import com.marcecuevas.easybuy.viewModel.ProductViewModelFactory
import com.marcecuevas.hotelsapp.utils.light
import kotlinx.android.synthetic.main.fragment_product_detail.*
import org.kodein.di.generic.instance
import com.marcecuevas.easybuy.data.model.Error

class ProductDetailFragment: GenericFragment(){

    private var productID: String? = null
    private val viewModelFactory: ProductViewModelFactory by instance()
    private lateinit var viewModel: ProductViewModel

    private var reviews: ReviewDTO? = null

    override fun layout(): Int = R.layout.fragment_product_detail

    override fun init() {
        showProgress()

        getArgs()

        styleViews()

        startObserving()

        seeReviewsTV.setOnClickListener(){
            navigateToReviews(reviews)
        }
    }

    private fun getArgs() {
        productID = arguments?.let {
            ProductDetailFragmentArgs.fromBundle(it).productId }
    }

    private fun styleViews() {
        nameTV.light(context)
        seeReviewsTV.light(context)
        priceTV.light(context)
        seeReviewsTV.light(context)
        listPriceTV.light(context)
        discountTV.light(context)
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
                seeReviewsTV.text = "Ver ${it.items?.first()?.reviews?.size} opiniones"
                seeReviewsTV.visibility = View.VISIBLE
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

    private fun setupView(item: ProductDetailDTO){
        with(item){
            context?.let {
                Glide.with(it)
                    .load("http:${mainImage?.url}")
                    .into(imageView)
            }

            nameTV.text = description
            priceTV.text = "$${price}"
            listPriceTV.text = "$${listPrice}"

            discount?.let {
                if (it > 0){
                    listPriceTV.visibility = View.VISIBLE
                    discountTV.visibility = View.VISIBLE
                    discountTV.text = "${it}% OFF"
                }
            }
        }
        hideProgress()
    }




}
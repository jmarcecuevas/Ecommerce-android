package com.marcecuevas.easybuy.view.fragment

import android.graphics.Paint
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
import com.marcecuevas.easybuy.view.adapter.ReviewsAdapter
import kotlinx.android.synthetic.main.fragment_product_detail.overallRatiingTV
import kotlinx.android.synthetic.main.fragment_product_detail.reviewsAmountTV
import kotlinx.android.synthetic.main.fragment_product_detail.starsRatingBar
import kotlinx.android.synthetic.main.item_product.view.*

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

        seeAllReviewsContainer.setOnClickListener(){
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
                starsRatingBar.visibility = View.VISIBLE
                setupReviews(it)
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

    private fun setupReviews(item: ReviewDTO){
        val rating = reviews?.items?.first()?.reviewStatistics?.averageOverallRating
        overallRatiingTV.text = rating.toString()
        rating?.let { starsRatingBarAvg.rating = it }
        reviewsAmountTV.text = "Promedio entre ${reviews?.items?.first()?.reviews?.size} opiniones"

        setupAdapter()
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
                Glide.with(it)
                    .load("http:${mainImage?.url}")
                    .into(imageView)
            }

            nameTV.text = description
            priceTV.text = "$${price}"
            listPriceTV.text = "$${listPrice}"
            listPriceTV.setPaintFlags(listPriceTV.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)

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
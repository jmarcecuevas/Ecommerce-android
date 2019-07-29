package com.marcecuevas.easybuy.view.fragment

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcecuevas.easybuy.R
import com.marcecuevas.easybuy.data.model.DTO.ReviewDTO
import com.marcecuevas.easybuy.view.adapter.ReviewsRatingAdapter
import kotlinx.android.synthetic.main.fragment_product_reviews.*


class ProductReviewsFragment: GenericFragment(){

    var reviews: ReviewDTO? = null

    override fun layout(): Int = R.layout.fragment_product_reviews

    override fun init() {
        getArgs()
    }

    private fun getArgs() {
        reviews = arguments?.let {
            ProductReviewsFragmentArgs.fromBundle(it).review}

        setupViews(reviews)

        setupAdapter(reviews)
    }

    private fun setupViews(reviews: ReviewDTO?){
        val rating = reviews?.items?.first()?.reviewStatistics?.averageOverallRating
        overallRatiingTV.text = rating.toString()
        rating?.let { starsRatingBar.rating = it }

        reviewsAmountTV.text = "Promedio entre ${reviews?.items?.first()?.reviews?.size} opiniones"
    }

    private fun setupAdapter(reviews: ReviewDTO?){
        val adapter = ReviewsRatingAdapter(context,reviews?.items?.first()?.reviews?.size)
        reviewsRatingRV.layoutManager = LinearLayoutManager(context)
        reviewsRatingRV.adapter = adapter
        adapter.loadItems(reviews?.items?.first()?.reviewStatistics?.ratingDistribution)
    }
}
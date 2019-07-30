package com.marcecuevas.easybuy.view.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.marcecuevas.easybuy.R
import com.marcecuevas.easybuy.data.model.DTO.ReviewDTO
import com.marcecuevas.easybuy.view.adapter.ReviewsAdapter
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

        setupAverageReviewsAdapter(reviews)

        setupReviewsAdapter(reviews)
    }

    private fun setupViews(reviews: ReviewDTO?){
        reviews?.let { headerReviews.setupReviews(it) }
    }

    private fun setupAverageReviewsAdapter(reviews: ReviewDTO?){
        val adapter = ReviewsRatingAdapter(context,reviews?.items?.first()?.reviews?.size)
        reviewsRatingRV.layoutManager = LinearLayoutManager(context)
        reviewsRatingRV.isNestedScrollingEnabled = false
        reviewsRatingRV.adapter = adapter
        adapter.loadItems(reviews?.items?.first()?.reviewStatistics?.ratingDistribution)
    }

    private fun setupReviewsAdapter(reviews: ReviewDTO?){
        val adapter = ReviewsAdapter(context)
        reviewsRV.layoutManager = LinearLayoutManager(context)
        reviewsRV.adapter = adapter
        reviewsRV.isNestedScrollingEnabled = false
        adapter.loadItems(reviews?.items?.first()?.reviews)
    }
}
package com.marcecuevas.easybuy.view.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.marcecuevas.easybuy.R
import com.marcecuevas.easybuy.data.model.DTO.ReviewDTO
import kotlinx.android.synthetic.main.view_header_reviews.view.*

class HeaderReviewsView: FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_header_reviews, this);
    }

    @SuppressLint("SetTextI18n")
    fun setupReviews(reviews: ReviewDTO){
        val rating = reviews?.items?.first()?.reviewStatistics?.averageOverallRating
        overallRatiingTV.text = rating.toString()
        rating?.let {
            starsRatingBarAvg.rating = it
        }
        reviewsAmountTV.text = context?.getString(R.string.reviews_average,reviews?.items?.first()?.reviews?.size)
    }
}
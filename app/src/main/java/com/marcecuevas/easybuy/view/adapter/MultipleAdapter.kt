package com.marcecuevas.easybuy.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marcecuevas.easybuy.R
import com.marcecuevas.easybuy.data.model.DTO.GenericDTO
import com.marcecuevas.easybuy.data.model.DTO.RatingDistributionDTO
import com.marcecuevas.easybuy.data.model.DTO.ReviewDTO
import com.marcecuevas.easybuy.data.model.DTO.ReviewItemDTO
import com.marcecuevas.easybuy.utils.DateUtils
import com.marcecuevas.hotelsapp.utils.regular
import kotlinx.android.synthetic.main.item_review.view.*
import kotlinx.android.synthetic.main.item_review_rating.view.*
import kotlinx.android.synthetic.main.item_review_rating.view.reviewsAmountTV
import kotlinx.android.synthetic.main.view_header_reviews.view.*

class MultipleAdapter(private val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var review: ReviewDTO

    var reviewsSize: Int? = 0
    var reviewsDistributionSize: Int? = 0

    fun loadItems(review: ReviewDTO){
        this.review = review;

        this.reviewsSize = review.items?.first()?.reviews?.size
        this.reviewsDistributionSize = review.items?.first()?.reviewStatistics?.ratingDistribution?.size
        notifyDataSetChanged()
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_DISTRIBUTION = 1
        private const val TYPE_SEPARATOR = 2
        private const val TYPE_REVIEW = 3
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        reviewsSize?.let { reviewsSize ->
            reviewsDistributionSize?.let {distributionSize ->

                if(position == 0){
                    (holder as ReviewsHeaderViewHolder).bind(review)

                }else if(position > 0 && position <= distributionSize){
                    (holder as ReviewsDistributionViewHolder).bind(review.items?.first()?.
                        reviewStatistics?.ratingDistribution?.get(position - 1))
                }else {
                    (holder as ReviewsViewHolder).bind(review.items?.first()?.reviews?.get(position - 1 - distributionSize))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val view = LayoutInflater.from(context).inflate(R.layout.view_header_reviews, parent, false)
                return ReviewsHeaderViewHolder(view, context)
            }
            TYPE_DISTRIBUTION -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.item_review_rating, parent, false)
                return ReviewsDistributionViewHolder(view, review.items?.first()?.totalReviewCount, context)
            }
            TYPE_REVIEW -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.item_review, parent, false)
                ReviewsViewHolder(view, context)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        reviewsDistributionSize?.let {
            if(position == 0){
                return TYPE_HEADER
            }else if(position > 0 && position <= it){
                return TYPE_DISTRIBUTION
            }else{
                return TYPE_REVIEW
            }
        }
        return 0
    }

    override fun getItemCount(): Int {
        reviewsSize?.let {reviews ->
            reviewsDistributionSize?.let{
                return it.plus(reviews).plus(1)
            }
        }
        return 0
    }


    class ReviewsHeaderViewHolder(itemView: View, val context: Context?): RecyclerView.ViewHolder(itemView) {

        fun bind(item: ReviewDTO){
            val rating = item.items?.first()?.reviewStatistics?.averageOverallRating
            itemView.overallRatiingTV.text = rating.toString()
            rating?.let {
                itemView.starsRatingBarAvg.rating = it
            }
            itemView.reviewsAmountTV.text = context?.getString(R.string.reviews_average,item.items?.first()?.reviews?.size)
        }
    }

    class ReviewsDistributionViewHolder(itemView: View, val totalAmount: Int?, val context: Context?) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: RatingDistributionDTO?) {
            with(item) {
                itemView.indicatorNumberTV.text = this?.ratingValue.toString()
                itemView.reviewsAmountTV.text = context?.getString(R.string.amount_reviews_only, this?.count)

                totalAmount?.let {
                    itemView.seekBar.max = it
                    this?.count?.let { itemView.seekBar.progress = it }
                }

            }
        }
    }

    class ReviewsViewHolder(itemView: View, val context: Context?): RecyclerView.ViewHolder(itemView) {

        init {
            itemView.titleTV.regular(context)
            itemView.byTV.regular(context)
            itemView.nicknameTV.regular(context)
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: ReviewItemDTO?) {
            with(item){
                this?.rating?.toFloat()?.let {
                    itemView.starsRatingBar.rating = it
                }

                itemView.titleTV.text = this?.title?.substring(0,1)?.toUpperCase() +
                        this?.title?.substring(1)?.toLowerCase()
                itemView.descriptionTV.text = this?.reviewText
                itemView.nicknameTV.text = this?.usernickname

                this?.submissionTime?.let {
                    itemView.dateTV.text = DateUtils.toSimpleString(it)
                }
            }
        }
    }
}

package com.marcecuevas.easybuy.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcecuevas.easybuy.R
import com.marcecuevas.easybuy.data.model.DTO.RatingDistributionDTO
import kotlinx.android.synthetic.main.item_review_rating.view.*

class ReviewsRatingAdapter(context: Context?,val totalAmount: Int?) : GenericRecyclerAdapter<ReviewsRatingAdapter.ViewHolder, RatingDistributionDTO>(context) {

    override fun getHolder(parent: ViewGroup): ReviewsRatingAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_review_rating,parent,false)
        return ViewHolder(view,totalAmount,context)
    }

    class ViewHolder(itemView: View, val totalAmount: Int?, val context: Context?) :
        GenericRecyclerAdapter.GenericViewHolder<RatingDistributionDTO>(itemView) {

        override fun bind(item: RatingDistributionDTO?) {
            with(item){
                itemView.indicatorNumberTV.text = this?.ratingValue.toString()
                itemView.reviewsAmountTV.text = context?.getString(R.string.amount_reviews_only, this?.count)

                totalAmount?.let{
                    itemView.seekBar.max = it
                    this?.count?.let { itemView.seekBar.progress = it }
                }

            }
        }
    }
}

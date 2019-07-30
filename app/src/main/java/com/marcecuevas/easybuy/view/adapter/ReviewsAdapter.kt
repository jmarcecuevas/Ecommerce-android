package com.marcecuevas.easybuy.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcecuevas.easybuy.R
import com.marcecuevas.easybuy.data.model.DTO.ReviewItemDTO
import com.marcecuevas.easybuy.utils.DateUtils
import com.marcecuevas.hotelsapp.utils.light
import com.marcecuevas.hotelsapp.utils.regular
import kotlinx.android.synthetic.main.item_review.view.*
import android.annotation.SuppressLint

class ReviewsAdapter(context: Context?) : GenericRecyclerAdapter<ReviewsAdapter.ViewHolder, ReviewItemDTO>(context) {

    override fun getHolder(parent: ViewGroup): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_review,parent,false)
        return ViewHolder(view,context)
    }

    class ViewHolder(itemView: View, val context: Context?):GenericRecyclerAdapter.GenericViewHolder<ReviewItemDTO>(itemView) {

        init {
            itemView.titleTV.regular(context)
            itemView.byTV.regular(context)
            itemView.nicknameTV.regular(context)
        }

        @SuppressLint("SetTextI18n")
        override fun bind(item: ReviewItemDTO?) {
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
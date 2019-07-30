package com.marcecuevas.easybuy.view.component

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.marcecuevas.easybuy.R
import com.marcecuevas.easybuy.data.model.DTO.ProductDetailDTO
import com.marcecuevas.easybuy.data.model.DTO.ReviewDTO
import com.marcecuevas.hotelsapp.utils.light
import kotlinx.android.synthetic.main.item_product.view.discountTV
import kotlinx.android.synthetic.main.item_product.view.listPriceTV
import kotlinx.android.synthetic.main.item_product.view.nameTV
import kotlinx.android.synthetic.main.item_product.view.priceTV
import kotlinx.android.synthetic.main.view_header_detail.view.*

class HeaderDetailView: FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    lateinit var onClick: () -> Unit

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_header_detail, this)

        nameTV.light(context)
        seeReviewsTV.light(context)
        priceTV.light(context)
        seeReviewsTV.light(context)
        listPriceTV.light(context)
        discountTV.light(context)
    }

    @SuppressLint("SetTextI18n")
    fun setup(item: ProductDetailDTO){
        with(item){
            nameTV.text = description
            priceTV.text = "$${price}"
            listPriceTV.text = "$${listPrice}"
            listPriceTV.setPaintFlags(listPriceTV.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)

            handleDiscount(discount)
        }
    }

    fun handleDiscount(discount: Int?){
        discount?.let {
            if (it > 0){
                listPriceTV.visibility = View.VISIBLE
                discountTV.visibility = View.VISIBLE
                discountTV.text = "${it}% OFF"
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun setupReviews(item: ReviewDTO?){
        item?.let {
            seeReviewsTV.text = "Ver ${it.items?.first()?.reviews?.size} opiniones"
            seeReviewsTV.visibility = View.VISIBLE
            starsRatingBar.visibility = View.VISIBLE

            seeReviewsTV.setOnClickListener(){
                onClick()
            }
        }
    }
}
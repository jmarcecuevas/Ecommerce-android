package com.marcecuevas.easybuy.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.marcecuevas.easybuy.R
import com.marcecuevas.easybuy.data.model.DTO.ProductItemDTO
import com.marcecuevas.hotelsapp.utils.light
import kotlinx.android.synthetic.main.item_product.view.*
import android.graphics.Paint
import com.marcecuevas.hotelsapp.utils.bold
import com.marcecuevas.hotelsapp.utils.regular


class ProductsAdapter(context: Context?, val onClick: (ProductItemDTO?) -> Unit) : GenericRecyclerAdapter<ProductsAdapter.ViewHolder, ProductItemDTO>(context) {

    override fun getHolder(parent: ViewGroup): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent,false)
        return ViewHolder(view,context)
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.setOnClickListener(){
            onClick(items?.get(position))
        }
    }

    class ViewHolder(itemView: View, val context: Context?): GenericRecyclerAdapter.GenericViewHolder<ProductItemDTO>(itemView) {

        init {
            with(itemView){
                listPriceTV.setPaintFlags(listPriceTV.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
            }
        }

        @SuppressLint("SetTextI18n")
        override fun bind(item: ProductItemDTO?) {
            with(item){

                Glide.with(itemView)
                .load("http:${this?.imageURL}")
                .into(itemView.imageView)

                itemView.nameTV.text = this?.description
                itemView.priceTV.text = "$${this?.price}"
                itemView.listPriceTV.text = "$${this?.listPrice}"

                itemView.listPriceTV.visibility = View.GONE
                itemView.discountTV.visibility = View.GONE

                handleDiscount(this?.discount)
            }
        }

        fun handleDiscount(discount: Int?){
            discount?.let {
                if (it > 0){
                    itemView.listPriceTV.visibility = View.VISIBLE
                    itemView.discountTV.visibility = View.VISIBLE
                    itemView.discountTV.text = context?.getString(R.string.discount, it);
                }
            }
        }
    }
}
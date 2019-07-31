package com.marcecuevas.easybuy.view.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.marcecuevas.easybuy.R
import com.marcecuevas.easybuy.data.model.DTO.ReviewDTO
import com.marcecuevas.easybuy.view.adapter.MultipleAdapter
import kotlinx.android.synthetic.main.fragment_product_reviews.*


class ProductReviewsFragment: GenericFragment(){

    var reviews: ReviewDTO? = null

    override fun layout(): Int = R.layout.fragment_product_reviews

    override fun init() {

        getArgs()

        setupAdapter()
    }

    private fun getArgs() {
        reviews = arguments?.let {
            ProductReviewsFragmentArgs.fromBundle(it).review}
    }

    private fun setupAdapter(){
        val adapter = MultipleAdapter(context)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        reviews?.let {
            adapter.loadItems(it)
        }
    }
}
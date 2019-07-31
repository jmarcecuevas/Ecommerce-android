package com.marcecuevas.easybuy.data.repository

import com.marcecuevas.easybuy.data.model.DTO.ProductDTO
import com.marcecuevas.easybuy.data.model.DTO.ProductDetailDTO
import com.marcecuevas.easybuy.data.model.DTO.ReviewDTO
import com.marcecuevas.easybuy.data.model.Result

interface ProductRepository {

    suspend fun getProducts(): Result<ProductDTO>

    suspend fun getProductDetail(id: String): Result<ProductDetailDTO>

    suspend fun getReviewsFromProduct(id: String): Result<ReviewDTO>
}
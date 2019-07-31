package com.marcecuevas.easybuy.data.dao

import com.marcecuevas.easybuy.data.model.DTO.ProductDTO
import com.marcecuevas.easybuy.data.model.DTO.ProductDetailDTO
import com.marcecuevas.easybuy.data.model.DTO.ReviewDTO
import com.marcecuevas.easybuy.data.model.Result

interface ProductDAO {

    suspend fun getProducts(): Result<ProductDTO>

    suspend fun getProductDetail(id: String): Result<ProductDetailDTO>

    suspend fun getReviewsFromProduct(id: String): Result<ReviewDTO>
}
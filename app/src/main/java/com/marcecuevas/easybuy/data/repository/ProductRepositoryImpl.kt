package com.marcecuevas.easybuy.data.repository

import com.marcecuevas.easybuy.data.dao.ProductDAO
import com.marcecuevas.easybuy.data.model.DTO.ProductDTO
import com.marcecuevas.easybuy.data.model.DTO.ProductDetailDTO
import com.marcecuevas.easybuy.data.model.DTO.ReviewDTO
import com.marcecuevas.easybuy.data.model.Result

class ProductRepositoryImpl(private val productDAO: ProductDAO): ProductRepository {

    override suspend fun getProducts(): Result<ProductDTO> {
       return productDAO.getProducts()
    }

    override suspend fun getProductDetail(id: String): Result<ProductDetailDTO> {
        return productDAO.getProductDetail(id)
    }

    override suspend fun getReviewsFromProduct(id: String): Result<ReviewDTO> {
        return productDAO.getReviewsFromProduct(id)
    }
}
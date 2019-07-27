package com.marcecuevas.easybuy.data.dao

import com.marcecuevas.easybuy.data.model.DTO.ProductDTO
import com.marcecuevas.easybuy.data.model.DTO.ProductDetailDTO
import com.marcecuevas.easybuy.data.model.DTO.ReviewDTO
import com.marcecuevas.easybuy.data.network.ProductREST
import com.marcecuevas.hotelsapp.data.model.Result
import com.marcecuevas.hotelsapp.utils.NoConnectivityException
import java.io.IOException

class ProductDAOImpl(private val productREST: ProductREST) : ProductDAO {

    override suspend fun getProducts(): Result<ProductDTO> {
        try{
            val response = productREST.getProducts().await()
            if (response.isSuccessful)
                response.body()?.let {
                    return Result.Success(it)
                }
            return Result.Error(IOException("Ha ocurrido un error"))
        }catch (e: NoConnectivityException){
            return Result.Error(IOException("No hay internet"))
        }
    }

    override suspend fun getProductDetail(id: String): Result<ProductDetailDTO> {
        try{
            val response = productREST.getProductDetail(id).await()
            if(response.isSuccessful){
                response.body()?.let {
                    return Result.Success(it)
                }
            }
            return Result.Error(IOException("Ha ocurrido un error"))
        }catch (e: NoConnectivityException){
            return Result.Error(IOException("No hay internet"))
        }
    }

    override suspend fun getReviewsFromProduct(id: String): Result<ReviewDTO> {
        try{
            val response = productREST.getReviewsFromProduct(id).await()
            if(response.isSuccessful){
                response.body()?.let {
                    return Result.Success(it)
                }
            }
            return Result.Error(IOException("Ha ocurrido un error"))
        }catch (e: NoConnectivityException){
            return Result.Error(IOException("No hay internet"))
        }
    }
}
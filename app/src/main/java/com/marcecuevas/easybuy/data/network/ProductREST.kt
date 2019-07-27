package com.marcecuevas.easybuy.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.marcecuevas.easybuy.data.model.DTO.ItemDTO
import com.marcecuevas.easybuy.data.model.DTO.ProductDTO
import com.marcecuevas.easybuy.data.model.DTO.ProductDetailDTO
import com.marcecuevas.easybuy.data.model.DTO.ReviewDTO
import com.marcecuevas.hotelsapp.data.network.ConnectivityInterceptor
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductREST {

    @GET(value = "products")
    fun getProducts(): Deferred<Response<ProductDTO>>

    @GET("products/{id}")
    fun getProductDetail(@Path(value = "id") id: String): Deferred<Response<ProductDetailDTO>>

    @GET("products/{id}/reviews")
    fun getReviewsFromProduct(@Path(value = "id") productID: String): Deferred<Response<ReviewDTO>>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ProductREST {
            val requestInterceptor = Interceptor {chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://garbarino-mock-api.s3-website-us-east-1.amazonaws.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductREST::class.java)
        }
    }
}
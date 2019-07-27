package com.marcecuevas.easybuy.data.model.DTO

import com.google.gson.annotations.SerializedName

data class ProductDTO(
    val items: List<ProductItemDTO>?
): GenericDTO

data class ProductItemDTO(
    val id: String?,
    val description: String?,
    @SerializedName("image_url")
    val imageURL: String?,
    val price: Int?,
    @SerializedName("list_price")
    val listPrice: Int?,
    val discount: Int?

): GenericDTO
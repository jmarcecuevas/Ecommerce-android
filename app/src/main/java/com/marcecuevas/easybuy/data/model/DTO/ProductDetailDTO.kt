package com.marcecuevas.easybuy.data.model.DTO

import com.google.gson.annotations.SerializedName

data class ProductDetailDTO(
    val xid: String?,
    val description: String?,
    val summary: String,
    val brand: String?,
    @SerializedName("original_brand")
    val originalBrand: String?,
    @SerializedName("list_price")
    val listPrice: Int?,
    val price: String,
    val discount: Int?,
    @SerializedName("enabled_for_sale")
    val enabledForSale: String?,
    @SerializedName("preferred_installment")
    val preferredInstallment: PreferredInstallmentDTO?,
    val model: String?,
    @SerializedName("category_id")
    val categoryID: Int?,
    @SerializedName("main_image")
    val mainImage: MainImageDTO?,
    val virtual: Boolean?,
    val categories: List<String>?,
    val category: String?,
    @SerializedName("products_tags")
    val productTags: List<String>?,
    @SerializedName("price_matching_discount")
    val priceMatchingDiscount: Int?,
    @SerializedName("price_without_vat")
    val priceWithoutVat: Float?,
    @SerializedName("vat_percentage")
    val vatPercentage: Int?,
    val resources: ResourceDTO
): GenericDTO

data class PreferredInstallmentDTO(
    @SerializedName("base_price")
    val basePrice: Int?,
    val installments: Int?,
    val interest: Float?,
    val surchage: Float?,
    @SerializedName("final_price")
    val finalPrice: Float?,
    @SerializedName("installment_price")
    val installmentPrice: Float?,
    val eapr: Float?,
    val tfc: Float?,
    val description: String?,
    @SerializedName("gateway_installments")
    val gatewayInstallments: Float?,
    @SerializedName("visa_financing")
    val visaFinancing: Boolean?,
    val repayment: Int?
): GenericDTO

data class MainImageDTO(
    @SerializedName("max_width")
    val maxWidth: Int?,
    val url: String?
): GenericDTO

data class ResourceDTO(
    val images: List<MainImageDTO>?,
    val videos: List<VideoDTO>?
): GenericDTO

data class VideoDTO(
    val url: String?,
    @SerializedName("thumb_url")
    val thumbURL: String?
)
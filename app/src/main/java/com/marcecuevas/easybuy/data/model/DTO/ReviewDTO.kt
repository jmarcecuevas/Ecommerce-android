package com.marcecuevas.easybuy.data.model.DTO

import com.google.gson.annotations.SerializedName
import java.util.*

data class ReviewDTO(
    val items: List<ItemDTO>?
): GenericDTO

data class ItemDTO(
    val id: String?,
    @SerializedName("review_statistics")
    val reviewStatistics: ReviewStatistics?,
    val reviews: List<ReviewItemDTO>?
): GenericDTO

data class ReviewStatistics(
    @SerializedName("average_overall_rating")
    val averageOverallRating: Float?,
    @SerializedName("rating_distribution")
    val ratingDistribution: List<RatingDistribution>?
): GenericDTO

data class RatingDistribution(
    @SerializedName("rating_value")
    val ratingValue: Int?,
    val count: Int?
): GenericDTO

data class ReviewItemDTO(
    val id: String?,
    val usernickname: String,
    val title: String?,
    @SerializedName("review_text")
    val reviewText: String?,
    val rating: Int?,
    @SerializedName("submission_time")
    val submissionTime: Date?,
    @SerializedName("product_id")
    val productID: String?
): GenericDTO


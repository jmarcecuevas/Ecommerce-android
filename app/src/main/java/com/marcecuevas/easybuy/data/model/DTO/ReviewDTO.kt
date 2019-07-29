package com.marcecuevas.easybuy.data.model.DTO

import com.google.gson.annotations.SerializedName
import java.util.*

data class ReviewDTO(
    val items: List<ItemDTO>?
): GenericDTO

data class ItemDTO(
    val id: String?,
    @SerializedName("review_statistics")
    val reviewStatistics: ReviewStatisticsDTO?,
    val reviews: List<ReviewItemDTO>?,
    @SerializedName("total_review_count")
    val totalReviewCount: Int?
): GenericDTO

data class ReviewStatisticsDTO(
    @SerializedName("average_overall_rating")
    val averageOverallRating: Float?,
    @SerializedName("rating_distribution")
    var ratingDistribution: List<RatingDistributionDTO>?
): GenericDTO

data class RatingDistributionDTO(
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


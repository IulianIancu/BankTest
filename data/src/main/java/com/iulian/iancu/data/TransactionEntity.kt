package com.iulian.iancu.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TransactionEntity(
    @Expose
    @SerializedName("feedItemUid")
    val id: String,
    @Expose
    @SerializedName("amount")
    val amount: AmountEntity,
    @Expose
    @SerializedName("direction")
    val direction: String,
    @Expose
    @SerializedName("status")
    val status: String,
)

data class AmountEntity(
    @Expose
    @SerializedName("currency")
    val currency: String,
    @Expose
    @SerializedName("minorUnits")
    val minorUnits: Int
)
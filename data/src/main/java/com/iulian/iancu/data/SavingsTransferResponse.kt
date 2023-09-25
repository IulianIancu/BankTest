package com.iulian.iancu.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SavingsTransferResponse(
    @Expose
    @SerializedName("transferUid")
    val transferUid: String,
    @Expose
    @SerializedName("success")
    val success: Boolean
)
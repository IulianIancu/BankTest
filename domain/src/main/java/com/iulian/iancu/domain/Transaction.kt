package com.iulian.iancu.domain

data class Transaction(
    val id: String,
    val amount: Amount,
    val direction: String,
    val status: String
)

data class Amount(
    val currency: String,
    val minorUnits: Int,
)
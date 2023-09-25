package com.iulian.iancu.domain

import kotlinx.coroutines.flow.Flow

interface BankRepository {
    suspend fun getTransactions(): List<Transaction>
    suspend fun sendRoundUp(roundUp: Amount): Boolean
}
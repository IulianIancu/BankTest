package com.iulian.iancu.domain

import kotlinx.coroutines.flow.Flow

class GetTransactionsUseCase (private val bankRepository: BankRepository) {
    private suspend fun run(): Flow<List<Transaction>> {
        return bankRepository.getTransactions()
    }

    suspend operator fun invoke(): Flow<List<Transaction>> {
        return run()
    }
}
package com.iulian.iancu.data

import android.accounts.NetworkErrorException
import com.iulian.iancu.domain.Amount
import com.iulian.iancu.domain.BankRepository
import com.iulian.iancu.domain.Transaction

class BankRepositoryImpl(private val bankService: BankService) : BankRepository {
    override suspend fun getTransactions(): List<Transaction> {
        val result = bankService.getTransactions()

        val body = result.body()
        if (result.isSuccessful && !body.isNullOrEmpty()) {
            return body.map {
                Transaction(
                    it.id,
                    Amount(it.amount.currency, it.amount.minorUnits),
                    it.direction,
                    it.status
                )
            }
        } else {
            throw NetworkErrorException("There was an issue with the API")
        }
    }

    override suspend fun sendRoundUp(roundUp: Amount): Boolean {
        val result = bankService.sendRoundUp(amount = roundUp)
        return result.isSuccessful
    }
}
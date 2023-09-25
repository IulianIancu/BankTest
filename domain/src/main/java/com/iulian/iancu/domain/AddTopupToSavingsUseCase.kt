package com.iulian.iancu.domain

class AddTopupToSavingsUseCase(private val bankRepository: BankRepository) {
    private suspend fun run(roundUp: Amount) {
        return bankRepository.sendRoundUp(roundUp)
    }

    suspend operator fun invoke(roundUp: Amount) {
        run(roundUp)
    }
}
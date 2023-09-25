package com.iulian.iancu.data

import com.iulian.iancu.domain.Amount
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface BankService {

    @Headers(
        "Accept: application/json",
        "Authorization: Bearer eyJhbGciOiJQUzI1NiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAA_21Ty5KjMAz8lS3Oo6lgHgFue9sf2A-QLZG4BmzKNpmd2tp_X4NJCKm50d1SS7LE30x7n3UZThqIR_vuA7pBm4tE8_Gu7Ji9ZX6WMUIVKNr2nANKWUGpmEEyEbSnXDS1QNmcOQbznynr8rqtzmUhRPWWaQyJqKu6WQhUys4m_LIDsfutafNupIjeLTVQlg2CFJxDXteVqFqqT7WK3sF-sEkZBVZFLoSEum1aKOWpBhRnBRUVUvRMTS_zmBHH-qkUe7_XaUssoS4lxaymAGzKCgpWZ5U3pNqmXgZWduLlUVKncF1bBYMjd46RfrwI4Wt6ETSxCbrX7I78oH04MBsgcrHJjkmHB0hKCKiuIz8i53C1Tvu4IdCG9E3TjEPSJA5o1NaJQkegrAnODsl3YTbNml67EYO2BmwP_Wxoq6dmH-x4b5tH1Fv2iIYwcEc8cOAHXMNGDhgRdirCRbzjNXPCL-a7lMBmksAeBHrEy-aZtP0TgkPjUS09P2gYrIrT796JALs8wyu7ZTnb6-FeKtU-UGuUY8V6Cgfgj9Kn09Hd4y2uwsPF7n0cuG3UA7f6PDNpuD4--zcWu_iN1y4mU3VlmgcmiGPvV-M5hDjgPG1wwvuZxN89XlE8JuvoqfyRvdc9st_kg_00Dz7w0gAof3ulJuoT9bzTdRWvS87-_Qd9K8mXoQQAAA.WIVIdShF-6TCLDnUqFpjl8tG1eudzUry01QjfbNyP3TaiFv5j7qHOac0fcR1i15hbOvqq-mMXnYhBeV1L2jcu5rj0n6g7pEy21pKIRF8x6xgLwkx771LciILTZCYXonIB1nuXVT2RQR_zeCCBDr0Z86X81FzFG25hcMPrBH_XlAYdeHPnjJqNdpoCYMXfuQ0IU31NhNDsnCE2ALeAspKZq02EHeYVN6TKhrtVUcBWZmZwaR37i3zaSfVvulkT_KvpsPLcE3BB0IYJP2YQuOI-TiFUHu29G5B7BcuK92BpUo8n3ThEb0-IlgefXPKIjNZ0RwLg2nLBTkmtjuaGO4pAHz3fm7niGSJun2tx0rZ8pQ7HF00nDx5SvBgX4Y33LZjONKcF-Izm9xLFPdSIXMTVGMURGiSAg-Fwe7ZOpg7cVDFVsMNVIqxWeUD5-HIKVuJROJTM3Bde-_NqaVQ0s3uJXd31lREr6TlewmaJNxSEZ0_ox-FG9QeYe7yXQJdqioy_ISKthL1SVkhJUHqFVxMwfuMVUhcJKK3iwDnMBxrdSDC9eyvz9mAVRGERDSAVuo_qe6F0MkYgN6IWFYQC-Y3H7neIPleDyE0kmfL-VVec9YCxiw_GIB4PTF2p--oDP_d8NhHhhgO0jAkvavs3Isusg9uGOf7Obu5bUfzEl3l0J0"
    )
    @GET("feed/account/{accountUid}/category/{categoryUid}")
    suspend fun getTransactions(
        @Path("accountUid") accountId: String = "c3a28b21-a9d8-448a-b2e1-1665259d606c",//Defaults from my sandbox
        @Path("categoryUid") category: String = "c3a2baec-5da7-433e-b7a4-29ea89d60681", //Defaults from my sandbox
        @Query("changesSince") changesSince: String = "2020-01-01T12:34:56.000Z" //Default to all of them
    ): Response<List<TransactionEntity>>

    @PUT("account/{accountUid}/saving-goals/{savingsGoalUid}/add-money/{transferUid}")
    suspend fun sendRoundUp(
        @Path("accountUid") accountId: String = "c3a28b21-a9d8-448a-b2e1-1665259d606c",//Defaults from my sandbox
        @Path("savingsGoalUid") savingsPot: String = "c3a2baec-5da7-433e-b7a4-29ea89d60681", // Let's pretend I have one
        @Path("transferUid") transferUid: String = "c3a2baec-5da7-433e-b7a4-29ea89d60681", // I'm supposed to generate this one I guess
        @Body amount: Amount
    ): Response<SavingsTransferResponse>

    companion object {
        var retrofitService: BankService? = null
        fun getInstance(): BankService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api-sandbox.starlingbank.com/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(BankService::class.java)
            }
            return retrofitService!!
        }
    }
}
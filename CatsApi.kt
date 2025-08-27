package com.raissa.gatos

import retrofit2.http.GET
import retrofit2.http.Query

interface CatsApi {
    @GET("images/search")
    suspend fun getRandomCat(): List<CatsResponse>

    @GET("images/search")
    suspend fun getBengalCats(
        @Query("breed_ids") breedIds: String = "beng",
        @Query("api_key") apiKey: String = "REPLACE_ME"
    ): List<Cats2Response>
}
package com.swordcatbreedsapplication.data.api

import com.swordcatbreedsapplication.data.api.models.CatBreed
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CatApi {

    // GET /breeds?limit=20&page=0
    @GET("breeds")
    suspend fun getCatBreeds(): List<CatBreed>

    // GET /breeds/:breed_id
    @GET("breeds/{breed_id}")
    suspend fun getCatBreed(
        @Path("breed_id") breedId: String
    ): CatBreed

    companion object {

        private const val API_V = "v1"
        private const val API_BASE_URL = "https://api.thecatapi.com/${API_V}/"
        private const val API_KEY =
            "live_PtIbadGYzWHvPIuChsJ4ItSLb1NhwHDBTG2sIWHNE08VVPub2EOrRAXtMhDPZbQ1"

        fun create(): CatApi {
            // Http client
            val client = OkHttpClient.Builder()
                // Add Logging
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                // Add API Key Header
                .addInterceptor {
                    val request = it.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("x-api-key", API_KEY)
                        .build()
                    it.proceed(request)
                }
                .build()
            // Retrofit
            return Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CatApi::class.java)
        }
    }
}
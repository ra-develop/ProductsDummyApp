package com.productsapp.data.sources.remote.api

import com.productsapp.data.sources.remote.models.product.dummyJson.ProductDTO
import com.productsapp.data.sources.remote.models.product.dummyJson.ProductsDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("products")
    suspend fun getProductsByPage(@Query("limit") limit: Int, @Query("skip") skip: Int): ProductsDTO

    @GET("products/{productCode}")
    suspend fun getDetail(@Path("productCode") productCode: String): ProductDTO
}
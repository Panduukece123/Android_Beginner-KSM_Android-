package com.example.apk_pandu.api.services

import com.example.apk_pandu.api.model.ProductResponse
import retrofit2.Call
import retrofit2.http.GET
interface ProductService {
    @GET("products")
    fun getAll(): Call<ProductResponse>

}
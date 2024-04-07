package com.productsapp.data.sources.remote.repositories

import com.productsapp.data.sources.remote.api.ApiService
import com.productsapp.data.sources.remote.models.product.dummyJson.ProductDTO
import com.productsapp.data.sources.remote.models.product.dummyJson.ProductsDTO
import com.productsapp.domain.entities.product.dummyJson.Product
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class GetProductsByPageImplTest {

    private val apiService = mock<ApiService>()

    private val productDTO = ProductDTO(
        id = 1,
        title = "iPhone 9",
        description = "An apple mobile which is nothing like apple",
        price = 549,
        discountPercentage = 12.96,
        rating = 4.69,
        stock = 94,
        brand = "Apple",
        category = "smartphones",
        thumbnail = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
        images = listOf(
            "https://cdn.dummyjson.com/product-images/1/1.jpg",
            "https://cdn.dummyjson.com/product-images/1/2.jpg",
            "https://cdn.dummyjson.com/product-images/1/3.jpg",
            "https://cdn.dummyjson.com/product-images/1/4.jpg",
            "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"
        )
    )

    private val productsDTOData = ProductsDTO(
        products = List(30) {
            productDTO
        },
        total = 100,
        skip = 0,
        limit = 30
    )

    private val product = Product(
        id = 1,
        title = "iPhone 9",
        description = "An apple mobile which is nothing like apple",
        price = 549,
        discountPercentage = 12.96,
        rating = 4.69,
        stock = 94,
        brand = "Apple",
        category = "smartphones",
        thumbnail = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
        images = listOf(
            "https://cdn.dummyjson.com/product-images/1/1.jpg",
            "https://cdn.dummyjson.com/product-images/1/2.jpg",
            "https://cdn.dummyjson.com/product-images/1/3.jpg",
            "https://cdn.dummyjson.com/product-images/1/4.jpg",
            "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"
        )
    )
    @Test
    fun getProductsTest() = runTest {

        Mockito.`when`(apiService.getProductsByPage(limit= 30, skip = 0)).thenReturn(productsDTOData)

        val actualItems = GetProductsByPageImpl(apiService).getProducts(1)


        val expectedItems = List(30) {
            product
        }
        Assert.assertEquals(expectedItems, actualItems)

    }

    fun getProductsFailureTNumberPageest() = runTest {
        Mockito.`when`(apiService.getProductsByPage(limit= 30, skip = 0)).thenReturn(productsDTOData)



    }
}
/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.productsapp.feature.products.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.productsapp.domain.usecases.history.GetAllHistoryUseCase
import com.productsapp.domain.usecases.product.GetProductsByPageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getItems: GetProductsByPageUseCase,
    private val getAllHistoryUseCase: GetAllHistoryUseCase
): ViewModel() {

    private var _loadedPage = 0
    private val _maxLoadingPageNumbers = 3

    private val _productUiState = MutableStateFlow(ProductsUiState())
    val productUiState: StateFlow<ProductsUiState> = _productUiState.asStateFlow()

    private val _allHistoryUiSate = MutableStateFlow(HistoryUIState())
    val allHistoryUiState: StateFlow<HistoryUIState> = _allHistoryUiSate.asStateFlow()

    init {
        viewModelScope.launch {
            _loadedPage ++
            val items = getItems.execute(numberPage = _loadedPage)
            _productUiState.value = ProductsUiState(items = items)
            Timber.tag("ProductListViewModel").i("The data of page $_loadedPage is loaded")
        }
        loadingHistory()
        Timber.d("Initiate View Model")
    }

    fun loadingNextPage() {
        if (_loadedPage < _maxLoadingPageNumbers) {
            viewModelScope.launch {
                _loadedPage ++
                val newItems = getItems.execute(numberPage = _loadedPage)
                val items = _productUiState.value.items + newItems
                _productUiState.value = ProductsUiState(items = items)
                Timber.tag("ProductListViewModel").i("The data of page $_loadedPage is loaded")
            }
        } else {
            Timber.tag("ProductListViewModel").i("All data pages are loaded")
        }
    }

    fun loadingHistory() {
        val listHistory = getAllHistoryUseCase.execute()
        _allHistoryUiSate.value = HistoryUIState(listHistory = listHistory)
    }

}

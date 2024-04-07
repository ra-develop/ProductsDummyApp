package com.productsapp.feature.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.productsapp.domain.entities.History
import com.productsapp.domain.entities.product.dummyJson.Product
import com.productsapp.domain.usecases.history.PutHistoryUseCase
import com.productsapp.domain.usecases.product.GetProductDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase,
    private val putHistoryUseCase: PutHistoryUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(DetailViewState())
    val viewState: StateFlow<DetailViewState> = _viewState.asStateFlow()

    private fun historyRecord(item: Product) {
        val itemHistory: History = History(
            product = item,
        )
        putHistoryUseCase.execute(item = itemHistory)
    }

    fun getDetailOfItem(itemCode: String) {
        viewModelScope.launch {
            try {
                val item = getProductDetailUseCase.execute(productCode = itemCode)
                _viewState.value = DetailViewState(item = item)
                historyRecord(item)
            } catch (e: Exception) {
                Timber.tag("DetailViewModel").e(e)
                _viewState.value = DetailViewState(isError = true, errorDetail = e.message.orEmpty())
// TODO save to history used only for test of the history functionality,
//  should be deleted in the production
                val errorItem = Product(
                    id = itemCode.toInt(),
                    brand = "Item code: $itemCode",
                    category = "Error",
                )
                historyRecord(errorItem)
            }
        }
    }
}

data class DetailViewState (
    val isError: Boolean = false,
    val errorDetail: String = "",
    val item: Product? = null
)
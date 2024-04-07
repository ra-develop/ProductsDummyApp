package com.productsapp.feature.products.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.productsapp.domain.entities.History
import com.productsapp.domain.entities.mockData.ProductMock
import com.productsapp.feature.products.ui.viewModels.ProductListViewModel
import com.productsapp.feature.products.ui.widgets.ProductItemCard
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowListHistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductListViewModel = hiltViewModel(),
    onClickBack: () -> Unit = {},
    onNavigateToDetail: (String) -> Unit = {},
) {
    val allHistoryUIState by viewModel.allHistoryUiState.collectAsState()
    viewModel.loadingHistory()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Item viewed history",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onClickBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
        modifier = Modifier
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(10.dp),
            modifier = modifier.padding(innerPadding)
        ) {
            items(allHistoryUIState.listHistory) { itemHistory ->

                val datFormat = SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss")
                val dateString = datFormat.format(itemHistory.accessDate)
                Column {
                    Text(dateString)
                    ProductItemCard(
                        productItem = itemHistory.product,
                        onNavigateToDetail = onNavigateToDetail
                    )
                }

            }
        }
    }
}

@Preview(name = "ShowListHistory")
@Composable
private fun PreviewShowListHistory() {
    val listHistory = List(ProductMock().listValue.size){index ->
        History(product = ProductMock().listValue[index])
    }
    ShowListHistoryScreen(onClickBack = {}, onNavigateToDetail = {})
}
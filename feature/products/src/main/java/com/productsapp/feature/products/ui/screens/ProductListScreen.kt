package com.productsapp.feature.products.ui.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.productsapp.core.ui.theme.ApplicationTheme
import com.productsapp.feature.products.ui.viewModels.ProductListViewModel
import com.productsapp.feature.products.ui.widgets.ProductItemCard
import com.productsapp.feature.products.ui.widgets.ShowAppInfo
import timber.log.Timber

@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductListViewModel = hiltViewModel(),
    onNavigateToDetail: (String) -> Unit,
    onNavigateToHistory: () -> Unit = {},
    ) {
    val productsUiState by viewModel.productUiState.collectAsState()

    if (productsUiState.items.isNotEmpty()) {
        ProductListScreen(
            modifier = modifier,
            onLoadData = {
                viewModel.loadingNextPage()
            },
            onNavigateToDetail = onNavigateToDetail,
            onNavigateToHistory = onNavigateToHistory,
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProductListScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductListViewModel = hiltViewModel(),
    onLoadData: () -> Unit = {},
    onNavigateToDetail: (String) -> Unit = {},
    onNavigateToHistory: () -> Unit = {},
    ) {
    var showMenu by remember { mutableStateOf(false) }
    var showAppInfo by remember { mutableStateOf(false) }
    val productsUiState by viewModel.productUiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Products list",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text("Item history")
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.List,
                                    contentDescription = "Item view history"
                                )
                            },
                            onClick = {
                                showMenu = false
                                onNavigateToHistory()
                            },
                        )
                        DropdownMenuItem(
                            text = {
                                Text("About App")
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Info,
                                    contentDescription = "Application Information"
                                )
                            },
                            onClick = { showAppInfo = true },
                        )

                    }
                },
            )
        },
        modifier = Modifier
    ) {innerPadding ->
        val lazyColumnListState = rememberLazyListState()

        val reachedBottom by remember {
            derivedStateOf {
                lazyColumnListState.reachedBottom()
            }
        }

        if (showAppInfo) {
            showMenu = false
            ShowAppInfo {
                showAppInfo = false
            }
        }

        LaunchedEffect(reachedBottom) {
            if (reachedBottom) {
                Timber.tag("ProductsScreen").i("Try to load the new page of data")
                onLoadData()
            }
        }

        LazyColumn(
            state = lazyColumnListState,
            modifier = modifier.padding(innerPadding)
        ) {
            items(
                productsUiState.items,
            ) {

                ProductItemCard(productItem = it, onNavigateToDetail = onNavigateToDetail)
            }
        }
    }
}

internal fun LazyListState.reachedBottom(buffer: Int = 1): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
    return lastVisibleItem?.index != 0 && lastVisibleItem?.index == this.layoutInfo.totalItemsCount - buffer
}

// Previews

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    ApplicationTheme {
        ProductListScreen()
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitPreview() {
    ApplicationTheme {
        ProductListScreen()
    }
}

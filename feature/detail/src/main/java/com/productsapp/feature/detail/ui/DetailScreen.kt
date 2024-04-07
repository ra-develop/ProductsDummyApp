package com.productsapp.feature.detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.productsapp.core.ui.widgets.AlertMessage
import com.productsapp.core.utils.viewPriceCalculation
import com.productsapp.domain.entities.mockData.ProductMock
import com.productsapp.domain.entities.product.dummyJson.Product
import com.productsapp.feature.detail.ui.widgets.CarouselImageView

@Composable
fun DetailScreen(
    itemCode: String,
    viewModel: DetailViewModel = hiltViewModel(),
    onClickBack: () -> Unit = {}
) {
    val viewState = viewModel.viewState.collectAsStateWithLifecycle().value

    viewModel.getDetailOfItem(itemCode = itemCode)

    if (viewState.item != null) {

        DetailScreenImpl(
            item = viewState.item,
            onClickBack = onClickBack
        )
    } else if (viewState.isError){
        AlertMessage(
            onDismissRequest = { onClickBack() },
            dialogTitle = "Data loading error",
            dialogText = viewState.errorDetail,
            icon = Icons.Rounded.Warning
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
private fun DetailScreenImpl(
    item: Product,
    onClickBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Detail of item",
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

    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
//            val itemImgUrl = item.images[0] // TODO change to data for carousel view
//            val configuration = LocalConfiguration.current
//            val screenWidth = if (configuration.screenWidthDp.dp > 300.dp) 300.dp else configuration.screenWidthDp.dp
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CarouselImageView(
                    sliderList = item.images
                )
//                GlideSubcomposition( // TODO change to carousel view
//                    model = itemImgUrl,
//                    modifier = Modifier
//                        .padding(10.dp)
//                        .height(screenWidth)
//                        .width(screenWidth),
//                ) {
//                    // state comes from GlideSubcompositionScope
//                    when (state) {
//                        RequestState.Failure -> {
//                            Timber.tag("GlideSubcomposition").e(state.toString())
//                            Box(contentAlignment = Alignment.Center) {
//                                Text(
//                                    text = "Loading error",
//                                    textAlign = TextAlign.Center
//                                )
//                            }
//                        }
//
//                        RequestState.Loading -> {
//                            Box(modifier = Modifier.size(24.dp, 24.dp)) {
//                                CircularProgressIndicator()
//                            }
//                        }
//                        // painter also comes from GlideSubcompositionScope
//                        else -> {
//                            Box(
//                                contentAlignment = Alignment.Center,
//                                modifier = Modifier.fillMaxWidth()
//                            ) {
//                                Image(
//                                    painter,
//                                    contentDescription = null,
//                                    contentScale = ContentScale.Fit,
//                                )
//                            }
//                        }
//                    }
//                }
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxHeight()
            ) {
                item {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Brand name:")
                        Text(text = item.brand)
                    }
                }
                item {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Product title:")
                        Text(text = item.title)
                    }
                }
                item {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Category name:")
                        Text(text = item.category)
                    }
                }
                item {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Description:")
                        Text(text = item.description)
                    }
                }
                item {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Rating:")
                        Text(text = item.rating.toString())
                    }
                }
                if (item.discountPercentage != 0.0) {
                    item {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "Old Price:")
                            Text(text = item.price.toString())
                        }
                    }
                    item {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "Discount %:")
                            Text(text = item.discountPercentage.toString())
                        }
                    }
                }
                item {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Current Price:")
                        Text(text = viewPriceCalculation(item = item))
                    }

                }
                item {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "In stock:")
                        Text(text = item.stock.toString())
                    }

                }

            }
        }
    }
}

@Preview("light")
@Composable
private fun DetailScreenPreview() {
    val item = ProductMock().value
    DetailScreenImpl(
        item = item
    )
}
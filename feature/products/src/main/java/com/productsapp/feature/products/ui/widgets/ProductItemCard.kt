
package com.productsapp.feature.products.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideSubcomposition
import com.bumptech.glide.integration.compose.RequestState
import com.productsapp.core.utils.viewPriceCalculation
import com.productsapp.domain.entities.mockData.ProductMock
import com.productsapp.domain.entities.product.dummyJson.Product
import timber.log.Timber


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductItemCard (
    productItem: Product,
    modifier: Modifier = Modifier,
    onNavigateToDetail: (String) -> Unit = {}
) {

    val itemImageThumb = productItem.thumbnail

    Card(modifier = modifier
        .padding(8.dp)
        .fillMaxWidth(), elevation = CardDefaults.cardElevation(
        defaultElevation = 4.dp),
        onClick = {
            onNavigateToDetail(productItem.id.toString())
        }
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            // item image

            GlideSubcomposition(
                model = itemImageThumb,
                modifier = Modifier
                    .padding(10.dp)
                    .height(100.dp)
                    .width(100.dp),
            ){
                // state comes from GlideSubcompositionScope
                when (state) {
                    RequestState.Failure -> {
                        Timber.tag("GlideSubcomposition").e(state.toString())
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = "Loading error",
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    RequestState.Loading -> {
                        Box(modifier = Modifier.size(24.dp, 24.dp)) {
                            CircularProgressIndicator()
                        }
                    }
                    // painter also comes from GlideSubcompositionScope
                    else -> {
                        Image(painter, contentDescription = null, contentScale = ContentScale.Fit,)
                    }
                }
            }

            // short details of item
            Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                Text(
                    text = productItem.title,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = productItem.category,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = viewPriceCalculation(item = productItem),
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }


        }
    }
}

@Preview(name = "ProductItemCard")
@Composable
private fun PreviewProductItemCard() {
    val item = ProductMock().value
    ProductItemCard(item)
}


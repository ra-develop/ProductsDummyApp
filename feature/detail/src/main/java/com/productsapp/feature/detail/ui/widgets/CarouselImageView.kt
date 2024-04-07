package com.productsapp.feature.detail.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideSubcomposition
import com.bumptech.glide.integration.compose.RequestState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import timber.log.Timber
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun CarouselImageView(
    sliderList: List<String>
) {
    val pagerState = rememberPagerState(initialPage = 0)


    Column {
        HorizontalPager(
            count = sliderList.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) { page ->

            Card(
                colors = CardDefaults.cardColors(Color.Transparent),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(0.dp),
                modifier = Modifier
                    .graphicsLayer {

                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }

            ) {
                val configuration = LocalConfiguration.current
                val screenWidth = configuration.screenWidthDp.dp * 0.6f

                GlideSubcomposition(
                    model = sliderList[page],
                    modifier = Modifier
                        .padding(10.dp)
                        .height(screenWidth)
                        .width(screenWidth)
                        .offset {
                            // Calculate the offset for the current page from the
                            // scroll position
                            val pageOffset =
                                this@HorizontalPager.calculateCurrentOffsetForPage(page)
                            // Then use it as a multiplier to apply an offset
                            IntOffset(
                                x = (70.dp * pageOffset).roundToPx(),
                                y = 0,
                            )
                        },


                ) {
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
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Image(
                                    painter,
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                )
                            }
                        }
                    }
                }
            }

        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
    }


}


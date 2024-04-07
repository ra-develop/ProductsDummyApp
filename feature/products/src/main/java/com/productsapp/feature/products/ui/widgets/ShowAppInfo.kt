package com.productsapp.feature.products.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun ShowAppInfo(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(20.dp)
            ) {
                Text(
                    text = "Application information",
                    fontSize =  20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "Products and Detail",
                    fontSize =  20.sp,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .wrapContentSize(Alignment.CenterStart),
                    textAlign = TextAlign.Center,
                )
            }


        }
    }

}

@Preview(name = "ShowAppInfo")
@Composable
private fun PreviewShowAppInfo() {
    ShowAppInfo(onDismissRequest = {})
}
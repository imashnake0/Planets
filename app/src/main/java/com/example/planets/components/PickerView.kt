package com.example.planets.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.planets.R
import com.example.planets.pList
import dev.chrisbanes.snapper.*

@ExperimentalSnapperApi
@Composable
fun ColumnScope.PickerView() {
    val lazyListState: LazyListState = rememberLazyListState()
    val layoutInfo: LazyListSnapperLayoutInfo = rememberLazyListSnapperLayoutInfo(lazyListState)
    val contentPadding = PaddingValues(top = 390.dp, bottom = 240.dp)
    val maxItemFling = 3

    Box(
        modifier = Modifier
            .weight(5f)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            flingBehavior = rememberSnapperFlingBehavior(
                snapOffsetForItem = SnapOffsets.Start,
                lazyListState = lazyListState,
                endContentPadding = contentPadding.calculateBottomPadding(),
                snapIndex = { _, startIndex, targetIndex ->
                    targetIndex.coerceIn(startIndex - maxItemFling, startIndex + maxItemFling)
                }
            ),
            horizontalAlignment = Alignment.Start,
            contentPadding = contentPadding,
            state = lazyListState

        ) {
            itemsIndexed(pList) { index, item->
                PickerTextItem(
                    text = item,
                    current =  layoutInfo.currentItem?.index,
                    index = index
                )
            }
        }

        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp),
                painter = painterResource(id = R.drawable.picker_icon),
                contentDescription = "Picker Outline"
            )

            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .fillMaxWidth()
                    .matchParentSize()
                    .padding(end = 48.dp, top = 16.dp, bottom = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pic4),
                    contentDescription = "Planet"
                )
            }
        }
    }
}

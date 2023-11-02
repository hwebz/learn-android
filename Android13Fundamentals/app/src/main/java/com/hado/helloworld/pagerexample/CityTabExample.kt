package com.hado.helloworld.pagerexample

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hado.helloworld.R
import kotlinx.coroutines.launch

@Composable
fun TabCarousel(
    title: String,
    isSelected: Boolean
) {
    val context = LocalContext.current
    val color = if (isSelected) R.color.purple_700 else R.color.white
    val textColor = if (isSelected) R.color.white else R.color.black

    Row(
        modifier = Modifier
            .background(
                color = Color(context.resources.getColor(color)),
                shape = RoundedCornerShape(25.dp)
            )
            .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
            .width(if (title.length < 11) 90.dp else 110.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = title,
            color = Color(context.resources.getColor(textColor)),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TabHeader(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val color = if (isSelected) R.color.purple_700 else R.color.white
    val ripple = rememberRipple(bounded = true, color = Color(context.resources.getColor(color)))
    val interactionSource: MutableInteractionSource = remember {
        MutableInteractionSource()
    }
    Box(
        modifier = Modifier
            .selectable(
                selected = isSelected,
                enabled = true,
                onClick = { onClick() },
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = ripple
            )
            .padding(top = 10.dp, bottom = 10.dp)
    ) {
        TabCarousel(title = title, isSelected = isSelected)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CityTabCarousel(
    pages: MutableList<String> = arrayListOf(
        "Spain",
        "New York",
        "Tokyo",
        "Switzerland",
        "Singapore",
        "Paris"
    )
) {
    val context = LocalContext.current
    var pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .fillMaxHeight(0f)
                )
            },
            edgePadding = 0.dp,
            containerColor = Color(
                context.resources.getColor(R.color.white)
            )
        ) {
            pages.forEachIndexed { index, title ->
                val isSelected = pagerState.currentPage == index

                TabHeader(
                    title,
                    isSelected,
                    onClick = { coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }}
                )
            }
        }

        HorizontalPager(
            pageCount = pages.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White)
        ) { page ->
            Text(
                text = "Display City Name: ${pages[page]}",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDegreePlanCarousel() {
    CityTabCarousel()
}
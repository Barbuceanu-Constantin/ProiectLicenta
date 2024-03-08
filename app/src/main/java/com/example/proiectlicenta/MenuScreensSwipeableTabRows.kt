package com.example.proiectlicenta

import android.graphics.drawable.Icon
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.RememberMe
import androidx.compose.material.icons.filled.Summarize
import androidx.compose.material.icons.outlined.AutoGraph
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.RememberMe
import androidx.compose.material.icons.outlined.Summarize
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)
@OptIn(ExperimentalFoundationApi::class)
class MenuScreensSwipeableTabRows {
    private val tabItems = listOf(
        TabItem(
            title = "Acasa",
            unselectedIcon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home
        ),
        TabItem(
            title = "Tranzactii",
            unselectedIcon = Icons.Outlined.Money,
            selectedIcon = Icons.Filled.Money
        ),
        TabItem(
            title = "Categories",
            unselectedIcon = Icons.Outlined.Category,
            selectedIcon = Icons.Filled.Category
        ),
        TabItem(
            title = "Mementouri",
            unselectedIcon = Icons.Outlined.RememberMe,
            selectedIcon = Icons.Filled.RememberMe
        ),
        TabItem(
            title = "Valute",
            unselectedIcon = Icons.Outlined.CurrencyExchange,
            selectedIcon = Icons.Filled.CurrencyExchange
        ),
        TabItem(
            title = "Sumar Buget",
            unselectedIcon = Icons.Outlined.Summarize,
            selectedIcon = Icons.Filled.Summarize
        ),
        TabItem(
            title = "Calendar",
            unselectedIcon = Icons.Outlined.CalendarMonth,
            selectedIcon = Icons.Filled.CalendarMonth
        ),
        TabItem(
            title = "Grafice",
            unselectedIcon = Icons.Outlined.AutoGraph,
            selectedIcon = Icons.Filled.AutoGraph
        )
    )

    @Composable
    fun showMenu() {
        var selectedTabIndex by remember { mutableIntStateOf(0) }
        val pagerState = rememberPagerState {
            tabItems.size
        }
        LaunchedEffect(selectedTabIndex) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }
        LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
            if(pagerState.isScrollInProgress) {
                selectedTabIndex = pagerState.currentPage
            }
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ScrollableTabRow(selectedTabIndex = selectedTabIndex) {
                tabItems.forEachIndexed { index, item ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(text = item.title)
                        },
                        icon = {
                            Icon(
                                imageVector = if(index == selectedTabIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        }
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth().weight(1f),
            ) { index ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Text(text = tabItems[index].title)
                }
            }
        }
    }
}